package ru.kudesnik.nasa.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.kudesnik.nasa.BuildConfig.NASA_API_KEY
import ru.kudesnik.nasa.repository.PictureOfTheDayResponseData
import ru.kudesnik.nasa.repository.PictureOfTheDayRetrofitImpl

class PictureOfTheDayViewModel(
    private val liveData: MutableLiveData<PictureOfTheDayState> = MutableLiveData(),
    private val pictureOfTheDayRetrofitImpl: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl()
) : ViewModel() {
    fun getLiveData(): LiveData<PictureOfTheDayState> {
        sendServerRequest()
        return liveData
    }

  fun sendServerRequest() {
        liveData.value=PictureOfTheDayState.Loading(null)
        pictureOfTheDayRetrofitImpl.getRetrofitImpl().getPictureOfTheDay(NASA_API_KEY).enqueue(

            object : Callback<PictureOfTheDayResponseData> {
                override fun onResponse(
                    call: Call<PictureOfTheDayResponseData>,
                    response: Response<PictureOfTheDayResponseData>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveData.value = PictureOfTheDayState.Success(response.body()!!)

//                        response.body()?.let {
//
//                            liveData.postValue(PictureOfTheDayState.Success(it))
//                        }
                    } else {
                        //вывести снекбар, что что-то пошло не так
                    }
                }

                override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
//HomeWork
                }
            }
        )

    }
}
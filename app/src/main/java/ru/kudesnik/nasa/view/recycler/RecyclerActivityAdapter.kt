package ru.kudesnik.nasa.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import ru.kudesnik.nasa.databinding.ActivityRecyclerItemEarthBinding
import ru.kudesnik.nasa.databinding.ActivityRecyclerItemMarsBinding

class RecyclerActivityAdapter(val onClickItemListener: OnClickItemListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var listData: List<Data>
    fun setData(listData: List<Data>) {
        this.listData = listData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_EARTH -> {
                val binding = ActivityRecyclerItemEarthBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                EarthViewHolder(binding.root)
            }
            else -> {
                val binding = ActivityRecyclerItemMarsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                MarsViewHolder(binding.root)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_EARTH -> {
                (holder as EarthViewHolder).bind(listData[position])
            }
            TYPE_MARS -> {
                (holder as MarsViewHolder).bind(listData[position])
            }
            else -> {

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

    override fun getItemCount(): Int = listData.size

    inner class EarthViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Data) {
            ActivityRecyclerItemEarthBinding.bind(itemView).apply {
                nameTextView.text = data.name
                descriptionTextView.text = data.description
                earthImageView.setOnClickListener {
                    onClickItemListener.onItemClick(data)
                }
            }
        }
    }


    inner class MarsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Data) {
            ActivityRecyclerItemMarsBinding.bind(itemView).apply {
                nameTextView.text = data.name
                marsImageView.setOnClickListener {
                    onClickItemListener.onItemClick(data)
                }
            }
        }
    }
}
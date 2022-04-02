package ru.kudesnik.nasa.view.layout.coordinator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import java.lang.Math.abs

class ImageBehavior(context: Context, attr: AttributeSet) :
    CoordinatorLayout.Behavior<View>(context, attr) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    )= dependency is AppBarLayout

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        var bar = dependency as AppBarLayout
        var barHeight = bar.height.toFloat()
        var barY = bar.y
//        child.y = bar.height.toFloat()+bar.y
        if(abs(barY)>barHeight/2) {
            child.visibility = View.VISIBLE
            child.alpha =1-(barHeight-abs(barY))/(barHeight)

        } else {
            child.visibility = View.GONE

        }

        return super.onDependentViewChanged(parent, child, dependency)
    }

}
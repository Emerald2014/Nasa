package ru.kudesnik.nasa.view.layout.coordinator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class NestedBehavior(context: Context, attr: AttributeSet) :
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
//var image = dependency as ImageView
        var bar = dependency as AppBarLayout
        child.y = bar.height.toFloat()+bar.y
        if(child.y<bar.height/2) {
//            image.visibility = View.VISIBLE
        }

        return super.onDependentViewChanged(parent, child, dependency)
    }

}
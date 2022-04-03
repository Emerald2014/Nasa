package ru.kudesnik.nasa.view.layout.coordinator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class ButtonBehavior(context: Context, attr: AttributeSet?) :
    CoordinatorLayout.Behavior<View>(context, attr) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ) = dependency is AppBarLayout

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        var bar = dependency as AppBarLayout
        var barHeight = bar.height.toFloat()
        var barY = bar.y
        var diff = (barHeight - Math.abs(barY)) / (barHeight) //от 1 до 0
        child.alpha = diff

        return super.onDependentViewChanged(parent, child, dependency)
    }

}
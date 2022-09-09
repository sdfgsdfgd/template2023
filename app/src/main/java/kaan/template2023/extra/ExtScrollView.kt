package kaan.template2023.extra

import android.view.ViewTreeObserver
import androidx.core.widget.NestedScrollView

/**
 * Register scroll state change with viewTreeObserver.
 * Don't forget to call [unregisterScrollStateChange] on a opposite lifecycle.
 */
fun NestedScrollView.registerScrollStateChange(onScrollChange: (canScroll: Boolean) -> Unit): ViewTreeObserver.OnGlobalLayoutListener {
    val globalChangeListener = ViewTreeObserver.OnGlobalLayoutListener {
        val viewHeight: Int = measuredHeight
        val contentHeight: Int = getChildAt(0).height
        onScrollChange(viewHeight - contentHeight < 0)
    }
    viewTreeObserver.addOnGlobalLayoutListener(globalChangeListener)
    return globalChangeListener
}

fun NestedScrollView.unregisterScrollStateChange(changeListener: ViewTreeObserver.OnGlobalLayoutListener) {
    viewTreeObserver.removeOnGlobalLayoutListener(changeListener)
}

package net.sdfgsdfg.luxr.extra

import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment

/**
 * Use to handle onBackPressed in the fragment onCreate
 */
fun Fragment.onBackPressed(shouldPopBlock: () -> Boolean) {
    requireActivity()
        .onBackPressedDispatcher
        .addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (shouldPopBlock.invoke()) {
                        // if you want onBackPressed() to be called as normal afterwards
                        if (isEnabled) {
                            isEnabled = false
                            requireActivity().onBackPressed()
                        }
                    }
                }
            }
        )
}

/**
 *  Hide system UI
 */
fun Fragment.hideSystemUI() {
    WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
    WindowInsetsControllerCompat(requireActivity().window, requireActivity().window.decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun Fragment.hideActionBar() {
    (requireActivity() as AppCompatActivity).supportActionBar?.hide()
}

fun Fragment.showActionBar() {
    (requireActivity() as AppCompatActivity).supportActionBar?.show()
}

/**
 * Show system UI
 */
fun Fragment.showSystemUI() {
    WindowCompat.setDecorFitsSystemWindows(requireActivity().window, true)
    WindowInsetsControllerCompat(requireActivity().window, requireActivity().window.decorView).show(WindowInsetsCompat.Type.systemBars())
}
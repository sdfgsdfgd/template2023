package net.sdfgsdfg.luxr.extra

import android.view.KeyEvent
import android.widget.EditText

fun EditText.onKeyboardDone(onKeyBoardDone: () -> Unit) {
    setOnEditorActionListener { _, _, event ->
        if ((event?.action == KeyEvent.ACTION_DOWN) &&
            (event.keyCode == KeyEvent.KEYCODE_ENTER)
        ) {
            // Perform action on key press
            onKeyBoardDone.invoke()
            true
        } else {
            false
        }
    }
}

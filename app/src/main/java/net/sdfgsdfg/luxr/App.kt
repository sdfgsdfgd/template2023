package net.sdfgsdfg.luxr

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import net.sdfgsdfg.luxr.extra.isDebug

@HiltAndroidApp
class App : Application() {
    init {
        Log.d("XXX", "isDebug: [${isDebug}]")
    }

    //xx From  "template2022"  necessary ?
    companion object {
        lateinit var instance: App
            private set
    }
}

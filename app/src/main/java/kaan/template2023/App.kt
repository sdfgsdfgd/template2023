package kaan.template2023


import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import kaan.template2023.extra.isDebug

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

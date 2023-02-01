package net.sdfgsdfg.luxr.extra

import net.sdfgsdfg.luxr.App
import net.sdfgsdfg.luxr.BuildConfig

val App.Companion.isDebug: Boolean
    get() = BuildConfig.BUILD_TYPE == "debug"

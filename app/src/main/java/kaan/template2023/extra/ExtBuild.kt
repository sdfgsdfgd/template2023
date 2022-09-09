package kaan.template2023.extra

import kaan.template2023.App
import kaan.template2023.BuildConfig

val App.Companion.isDebug: Boolean
    get() = BuildConfig.BUILD_TYPE == "debug"

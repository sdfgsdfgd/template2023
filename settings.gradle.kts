// Luxr, the Nostr client for Android
rootProject.name = "Luxr"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {    // Default, can remove
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
}


// buildCache.local.isEnabled
// buildCache.remote.isEnabled

include(":app")
//include(":module0")
//include(":module1")

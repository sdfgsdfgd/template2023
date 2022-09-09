pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "template2023"


// buildCache.local.isEnabled
// buildCache.remote.isEnabled

include(":app")

//include(":module0")
//include(":module1")

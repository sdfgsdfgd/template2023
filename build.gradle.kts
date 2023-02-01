// Top-level build file
plugins {
    id("com.android.application") version "7.4.0" apply false
    id("com.android.library") version "7.4.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
}

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44.2")
    }
}

// Custom clean / overrides the clean ??  ( below was the original )
task("clean") {
    delete(rootProject.buildDir)
//        delete(rootProject.buildDir)    // Delete other module's directories.
}
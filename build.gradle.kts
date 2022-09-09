// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version ("7.2.2") apply false
    id("com.android.library") version ("7.2.2") apply false
    id("org.jetbrains.kotlin.android") version ("1.7.10") apply false       /*xx  Kotlin version  ( project ) */
}

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.43.2")
    }
}

// Custom clean / overrides the clean ??  ( below was the original )
task("clean") {
    delete(rootProject.buildDir)
//        delete(rootProject.buildDir)    // Delete other module's directories.
}

//task clean(type: Delete) {
//    delete rootProject.buildDir
//}
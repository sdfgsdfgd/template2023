// Module build file
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

repositories {
    google()
    mavenCentral()
}

android {
    namespace = "net.sdfgsdfg.luxr"
    compileSdk = 33

    defaultConfig {
        applicationId = "net.sdfgsdfg.luxr"
        minSdk = 23
        targetSdk = 33

        // ============================ Release ==================================== //
        versionCode = 1
        versionName = "0.0.1"
        //  ============================ - - - ====================================  //

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }

        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    buildToolsVersion = "33.0.0"
}

dependencies {
    // Google Material Design
    implementation("com.google.android.material:material:1.8.0")

    // Core
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.0")

    // UI
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Architecture
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    // Nav
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

    // Data Binding Runtime (req by  DataBindFragment base class' DB parameter
    implementation("androidx.databinding:databinding-runtime:7.4.0")

    // Dependency Injection - Hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")

    // Network
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")

    // Network Debug
    implementation("com.localebro:okhttpprofiler:1.0.8")

    // Picasso - Cached Media Retrieval
    implementation("com.squareup.picasso:picasso:2.71828") {
        exclude(module = "support-annotations")
        exclude(module = "exifinterface")
//      exclude(module: ['exifinterface', 'support-annotations'])
    }

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
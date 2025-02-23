plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.movieapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.movieapp"
        minSdk = 35
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.hilt.android)
    implementation(libs.google.dagger)
    annotationProcessor(libs.dagger.compiler)
    implementation(libs.room.runtime)

    implementation (libs.retrofit)
    implementation (libs.okhttp3.okhttp)
    implementation (libs.converter.gson)
    implementation (libs.adapter.rxjava2)
    implementation (libs.rxjava)
    implementation (libs.rxandroid)
    implementation (libs.room.runtime.v242)
    annotationProcessor (libs.room.compiler)
    implementation (libs.dagger.v2405)
    annotationProcessor (libs.dagger.compiler.v2405)
    implementation (libs.lifecycle.extensions)
    implementation (libs.recyclerview)
    implementation (libs.picasso)

    annotationProcessor (libs.room.compiler.v261)
    implementation (libs.material.v190)


}
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlin)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlinSymbolProcessing)
    alias(libs.plugins.gmsGoogleService)
    kotlin("kapt")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.solo.studybudy"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.solo.studybudy"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)

    // Testing libraries ->
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)

    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    // Navigation ->
    implementation(libs.navigation.compose)

    // Coil (for image loading) ->
    implementation(libs.coil.compose)

    // Retrofit ->
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Paging 3 (For pagination) ->
    implementation(libs.paging.runtime.ktx)
    implementation(libs.paging.compose)

    // Hilt (DI) ->
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    // Coroutines ->
    implementation(libs.kotlinx.coroutines.android)

    // Timber for logs ->
    implementation(libs.timber)

    // Coroutine test ->
    testImplementation(libs.kotlinx.coroutines.test)
    // Mock ->
    testImplementation(libs.mockk)
    // Turbine for Flow testing
    testImplementation(libs.turbine)

    // Room dependencies ->
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    // Firebase ->
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)
}
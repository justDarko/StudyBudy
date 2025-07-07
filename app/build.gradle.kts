plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlin)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlinSymbolProcessing)
    alias(libs.plugins.gmsGoogleService)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
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
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    // Android core bundle ->
    implementation(libs.bundles.androidx.core)

    // Compose bundle ->
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    // Compose navigation ->
    // Navigation Dependency from hilt also work for navigation.
    // Compose navigation is defined for more clarity and consistency
    implementation(libs.navigation.compose)

    // Hilt (DI) ->
    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)

    // UI utils bundle ->
    implementation(libs.bundles.ui.utils)

    // Loggers bundle ->
    implementation(libs.bundles.loggers)

    // Firebase ->
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)

    // Unit tests bundle ->
    implementation(libs.bundles.unit.test)

    // Android tests bundle ->
    implementation(libs.bundles.android.test)
    
    // Data Store ->
    implementation(libs.datastore.core)
    implementation(libs.datastore.preferences)

    implementation(project(":core"))
    implementation(project(":login"))
    implementation(project(":home"))
    implementation(project(":userInterests"))
}
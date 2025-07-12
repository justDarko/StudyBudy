plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrainsKotlin)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlinSymbolProcessing)
}

android {
    namespace = "com.solo.home"
    compileSdk = 35

    defaultConfig {
        minSdk = 27

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(":core"))

    // Android core bundle ->
    implementation(libs.bundles.androidx.core)

    // Compose bundle ->
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    // Unit tests bundle ->
    implementation(libs.bundles.unit.test)

    // Android tests bundle ->
    implementation(libs.bundles.android.test)

    // navigation ->
    implementation(libs.navigation.compose)

    // Hilt (DI) ->
    implementation(libs.bundles.hilt)
    ksp(libs.hilt.compiler)

    // Loggers bundle ->
    implementation(libs.bundles.loggers)
}
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrainsKotlin)
    alias(libs.plugins.kotlinSymbolProcessing)
    alias(libs.plugins.gmsGoogleService)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.solo.core"
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
}

dependencies {
    // Android core bundle ->
    implementation(libs.bundles.androidx.core)

    // Compose bundle ->
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    // Unit tests bundle ->
    implementation(libs.bundles.unit.test)

    // Android tests bundle ->
    implementation(libs.bundles.android.test)

    // Loggers bundle ->
    implementation(libs.bundles.loggers)

    // Retrofit ->
    implementation(libs.bundles.network)

    // Coroutines ->
    implementation(libs.kotlinx.coroutines.android)

    // Timber for logs ->
    implementation(libs.bundles.loggers)

    // Room dependencies ->
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // Firebase ->
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)

    // Serialization ->
    implementation(libs.kotlinx.serialization.json)

    // Data Store ->
    implementation(libs.datastore.core)
    implementation(libs.datastore.preferences)
}
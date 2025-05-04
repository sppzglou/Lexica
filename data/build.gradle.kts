plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "gr.sppzglou.data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    buildTypes {
        release {
            buildConfigField("String", "BaseUrl", "\"https://api.dictionaryapi.dev/api/v2/\"")
        }
        debug {
            buildConfigField("String", "BaseUrl", "\"https://api.dictionaryapi.dev/api/v2/\"")
        }
    }
    buildFeatures {
        buildConfig = true
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(project(":domain"))

    //Hilt
    implementation(libs.bundles.dagger.hilt)
    kapt(libs.hilt.compiler)
    //DB room
    implementation(libs.bundles.room)
    ksp(libs.roomCompiler)
    //OkHttp
    implementation(libs.okhttp)
    implementation(libs.okhttpLoggingInterceptor)
    ///Retrofit
    implementation(libs.retrofitRxjava2Adapter)
    implementation(libs.retrofitGsonConverter)
    //Serialization
    implementation(libs.kotlinx.serialization.json)
}
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.jinyeob.nathanks.data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        @Suppress("DEPRECATION")
        targetSdk = libs.versions.targetSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()
    }

    buildTypes {
        release {
            buildConfigField("Boolean", "DEBUG_MODE", "false")
        }
        debug {
            buildConfigField("Boolean", "DEBUG_MODE", "true")
        }
    }

    buildFeatures {
        buildConfig = true
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
    implementation(project(":domain"))

    implementation(libs.coroutines.core)

    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)

    api(libs.retrofit2)
    api(libs.retrofit2.converter.moshi)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.gson)
    implementation(libs.retrofit2.converter.gson)
    api(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)

    implementation(libs.room.ktx)
    api(libs.room.runtime)
    ksp(libs.room.compiler)
    testImplementation(libs.room.testing)
}

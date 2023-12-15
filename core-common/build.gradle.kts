plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.jinyeob.nathanks.core.common"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        targetSdk = libs.versions.targetSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()
    }

    buildTypes {
        getByName("release") {
            buildConfigField("Boolean", "DEBUG_MODE", "false")
        }
        getByName("debug") {
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
    implementation(libs.android.core)
    implementation(libs.android.appcompat)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)

    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)
}

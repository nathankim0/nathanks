plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidHilt)
    id("kotlin-kapt")
//    id("com.google.gms.google-services")
//    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.jinyeob.nathanks"
    compileSdk = libs.versions.compileSdk.get().toInt()
    buildToolsVersion = libs.versions.buildToolsVersion.get()

    defaultConfig {
        applicationId = "com.jinyeob.nathanks"
        targetSdk = libs.versions.targetSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()

        versionCode = buildversionCode()
        versionName = buildversionName()
    }
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = true
            proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
            proguardFile("proguard-rules.pro")

            buildConfigField("Boolean", "DEBUG_MODE", "false")
            manifestPlaceholders["DEBUG_MODE"] = false
            manifestPlaceholders["CRASHLYTICS_ENABLED"] = true
            manifestPlaceholders["APP_NAME"] = "@string/app_name"
            manifestPlaceholders["APP_ICON"] = "@mipmap/ic_launcher"
            manifestPlaceholders["APP_ROUND_ICON"] = "@mipmap/ic_launcher_round"
        }
        debug {
            applicationIdSuffix = ".dev"
            versionNameSuffix = ".${getBuildNumber()}-dev${getBetaVersionPropertyInfo()}"
            isMinifyEnabled = false

            buildConfigField("Boolean", "DEBUG_MODE", "true")
            manifestPlaceholders["DEBUG_MODE"] = true
            manifestPlaceholders["CRASHLYTICS_ENABLED"] = true
            manifestPlaceholders["APP_NAME"] = "@string/app_name_dev"
            manifestPlaceholders["APP_ICON"] = "@mipmap/ic_launcher_dev"
            manifestPlaceholders["APP_ROUND_ICON"] = "@mipmap/ic_launcher_dev_round"
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
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
}

tasks.configureEach {
    when {
        // 서명된 APK 또는 번들 생성 작업에 대해
        this.name.startsWith("generateSigned") || this.name.startsWith("assembleRelease") -> {
            this.doLast {
                incrementBuildNumberFile()
            }
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":core-common"))

    implementation(libs.bundles.android)
    implementation(libs.bundles.android.google)
    implementation(libs.android.billing)
    implementation(libs.bundles.auth)
    implementation(libs.bundles.coroutines)

    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)

    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase)
    implementation(libs.bundles.mmp)
    implementation(libs.okhttp3)
    implementation(libs.glide)
    ksp(libs.glide.compiler)
    implementation(libs.bundles.android.navigation)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.work)
    implementation(libs.bundles.android.lifecycle)
    implementation(libs.bundles.android.intuit)
    implementation(libs.bundles.android.work)
    implementation(libs.bundles.android.etc)
}

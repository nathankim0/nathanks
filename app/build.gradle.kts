plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("de.mannodermaus.android-junit5")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.appdistribution")
}

android {
    namespace = "com.jinyeob.nathanks"
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion

    defaultConfig {
        applicationId = "com.jinyeob.nathanks"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        testInstrumentationRunnerArguments["runnerBuilder"] =
            "de.mannodermaus.junit5.AndroidJUnit5Builder"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = true
            proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
            proguardFile("proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

ktlint {
    disabledRules.set(setOf("no-wildcard-imports"))
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(KotlinConfig.STDLIB)
    implementation("com.kizitonwose.calendar:view:2.3.0")

    FirebaseConfig.run {
        implementation(platform(BOM))
        implementation(ANALYTICS)
        implementation(MESSAGING)
        implementation(CRASHLYTICS)
        implementation(DYNAMIC_LINKS)
    }

    NetworkConfig.run {
        implementation(OKHTTP)
    }

    CoroutinesConfig.run {
        implementation(CORE)
        implementation(ANDROID)
    }

    DaggerHiltConfig.run {
        implementation(ANDROID)
        kapt(COMPILER)
    }

    ComposeConfig.run {
        implementation(UI)
        implementation(MATERIAL)
        implementation(UI_TOOLING)
        implementation(RUNTIME_LIVEDATA)
        implementation(ACTIVITY)
    }

    AndroidConfig.run {
        implementation(CORE)
        implementation(APPCOMPAT)
        implementation(CONSTRAINTLAYOUT)
        implementation(ACTIVITY)
        implementation(FRAGMENT)
        implementation(LEGACY_SUPPORT)
        implementation(CARD_VIEW)
        implementation(RECYCLER_VIEW)
        implementation(VIEW_PAGER)
    }

    GoogleConfig.run {
        implementation(MATERIAL)
        implementation(FLEXBOX)
        implementation(PLAY_CORE)
        implementation(PLAY_CORE_KTX)
    }

    GlideConfig.run {
        implementation(GLIDE)
        kapt(COMPILER)
    }

    NavigationConfig.run {
        implementation(FRAGMENT)
        implementation(UI)
        implementation(RUNTIME)
    }

    HiltLifecycleConfig.run {
        kapt(COMPILER)
    }

    LifecycleConfig.run {
        implementation(RUNTIME)
        implementation(LIVEDATA)
        implementation(VIEW_MODEL)
        implementation(SAVED_STATE)
    }

    IntuitConfig.run {
        implementation(SDP)
        implementation(SSP)
    }

    WorkConfig.run {
        implementation(RUNTIME)
        implementation(RUNTIME_KTX)
        implementation(HILT)
    }

    EtcConfig.run {
        implementation(EVENTBUS)
        implementation(CIRCLE_IMAGE_VIEW)
        implementation(CIRCLE_PROGRESS)
        implementation(LOTTIE)
        implementation(MP_ANDROID_CHART)
        implementation(SHIMMER)
        implementation(CAROUSEL_LAYOUT)
        implementation(PAGER_INDICATOR)
        implementation(TEDPERMISSION)
        implementation(READMORE_VIEW)
    }

    TestConfig.run {
        testRuntimeOnly(ENGINE)
        testImplementation(JUPITER)
        testImplementation(ASSERTJ)
        testImplementation(JUNIT4)
        testImplementation(TRUTH)
        testImplementation(MOCKK)
        androidTestImplementation(EXT_JUNIT)
        androidTestImplementation(ESPRESSO_CORE)
        androidTestImplementation(API)
        androidTestImplementation(ANDROID_TEST_CORE)
        androidTestRuntimeOnly(ANDROID_TEST_RUNNER)
    }
}

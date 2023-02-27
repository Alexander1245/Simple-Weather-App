plugins {
    id(AndroidGradle.ANDROID_LIBRARY)
    kotlin(Kotlin.ANDROID_PLUGIN)
}

android {
    namespace = AndroidPrefs.CORE_NAMESPACE
    compileSdk = AndroidPrefs.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidPrefs.MIN_SDK
        targetSdk = AndroidPrefs.TARGET_SDK

        testInstrumentationRunner = AndroidPrefs.TEST_INSTRUMENTATION_RUNNER
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JVM.TARGET
    }
}

dependencies {

    implementation(AppDependencies.AndroidXCore.CORE)
    implementation(AppDependencies.AppCompat.APP_COMPAT)
    implementation(AppDependencies.Material.MATERIAL)
    implementation(AppDependencies.ConstraintLayout.CONSTRAINT_LAYOUT)

    AppDependencies.Navigation.IMPLEMENTATIONS.forEach(::implementation)
    AppDependencies.CoreMVVM.IMPLEMENTATIONS.forEach(::implementation)

    testImplementation(AppDependencies.JUnit4.JUNIT)

    androidTestImplementation(AppDependencies.JUnitAndroidExt.JUNIT)
    androidTestImplementation(AppDependencies.EspressoAndroid.CORE)
}
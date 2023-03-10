plugins {
    id(AndroidGradle.ANDROID_APPLICATION)
    kotlin(Kotlin.ANDROID_PLUGIN)
    kotlin(Kotlin.KAPT)
    id(AppDependencies.Hilt.PLUGIN)
    id(AppDependencies.Navigation.APP_PLUGIN)
}

android {
    namespace = AndroidPrefs.NAMESPACE
    compileSdk = AndroidPrefs.COMPILE_SDK

    defaultConfig {
        applicationId = AndroidPrefs.NAMESPACE
        minSdk = AndroidPrefs.MIN_SDK
        targetSdk = AndroidPrefs.TARGET_SDK
        versionCode = AndroidPrefs.VERSION_CODE
        versionName = AndroidPrefs.VERSION_NAME

        testInstrumentationRunner = AndroidPrefs.TEST_INSTRUMENTATION_RUNNER
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
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(AppDependencies.AndroidXCore.CORE)
    implementation(AppDependencies.AppCompat.APP_COMPAT)
    implementation(AppDependencies.Material.MATERIAL)
    implementation(AppDependencies.ConstraintLayout.CONSTRAINT_LAYOUT)

    AppDependencies.Navigation.IMPLEMENTATIONS.forEach(::implementation)
    AppDependencies.CoreMVVM.IMPLEMENTATIONS.forEach(::implementation)

    AppDependencies.Retrofit.IMPLEMENTATIONS.forEach(::implementation)

    testImplementation(AppDependencies.JUnit4.JUNIT)

    androidTestImplementation(AppDependencies.JUnitAndroidExt.JUNIT)
    androidTestImplementation(AppDependencies.EspressoAndroid.CORE)

    implementation(AppDependencies.Hilt.IMPLEMENTATION)
    kapt(AppDependencies.Hilt.KAPT_IMPL)

    implementation(AppDependencies.ViewBindingPropertyDelegate.NO_REFLECTION)

    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.DATA))
}

kapt {
    correctErrorTypes = true
}
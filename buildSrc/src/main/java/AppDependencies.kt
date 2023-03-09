object AppDependencies {

    object Navigation {
        const val VERSION = "2.5.3"

        const val UI = "androidx.navigation:navigation-ui-ktx:$VERSION"
        const val FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$VERSION"

        const val ROOT_CLASSPATH = "androidx.navigation:navigation-safe-args-gradle-plugin:$VERSION"
        const val APP_PLUGIN = "androidx.navigation.safeargs.kotlin"

        val IMPLEMENTATIONS = listOf(UI, FRAGMENT)
    }

    object Material {
        const val VERSION = "1.8.0"

        const val MATERIAL = "com.google.android.material:material:$VERSION"
    }

    object ConstraintLayout {
        const val VERSION = "2.1.4"

        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:$VERSION"
    }

    object JUnit4 {
        const val VERSION = "4.13.2"

        const val JUNIT = "junit:junit:$VERSION"
    }

    object JUnitAndroidExt {
        const val VERSION = "1.1.5"

        const val JUNIT = "androidx.test.ext:junit:$VERSION"
    }

    object EspressoAndroid {
        const val VERSION = "3.5.1"

        const val CORE = "androidx.test.espresso:espresso-core:$VERSION"
    }

    object AppCompat {
        const val VERSION = "1.6.1"

        const val APP_COMPAT = "androidx.appcompat:appcompat:$VERSION"
    }

    object AndroidXCore {
        const val VERSION = "1.9.0"

        const val CORE = "androidx.core:core-ktx:$VERSION"
    }

    object CoreMVVM {
        const val VERSION = "1.7"

        const val COROUTINES = "com.github.Alexander1245.MVVMLib:coroutines:$VERSION"
        const val MVVM_CORE = "com.github.Alexander1245.MVVMLib:mvvm-core:$VERSION"

        val IMPLEMENTATIONS = listOf(COROUTINES, MVVM_CORE)
    }

    object Coroutines {
        const val VERSION = "1.6.4"

        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"
        const val TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$VERSION"
    }

    object Retrofit {
        const val VERSION = "2.9.0"

        const val RETROFIT = "com.squareup.retrofit2:retrofit:$VERSION"
        const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:$VERSION"

        val IMPLEMENTATIONS = listOf(RETROFIT, GSON_CONVERTER)
    }

    object Hilt {
        const val VERSION = "2.44"

        const val PLUGIN = "com.google.dagger.hilt.android"

        const val IMPLEMENTATION = "com.google.dagger:hilt-android:$VERSION"
        const val KAPT_IMPL = "com.google.dagger:hilt-android-compiler:$VERSION"
    }

    object ViewBindingPropertyDelegate {
        const val VERSION = "1.5.8"

        const val NO_REFLECTION = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:$VERSION"
    }
}
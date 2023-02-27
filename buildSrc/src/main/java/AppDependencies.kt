object AppDependencies {

    object Navigation {
        const val VERSION = "2.5.3"

        const val UI = "androidx.navigation:navigation-ui-ktx:$VERSION"
        const val FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$VERSION"

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
        const val VERSION = "1.2"

        const val COROUTINES = "com.github.Alexander1245.MVVMLib:coroutines:$VERSION"
        const val MVVM_CORE = "com.github.Alexander1245.MVVMLib:mvvm-core:$VERSION"

        val IMPLEMENTATIONS = listOf(COROUTINES, MVVM_CORE)
    }
}
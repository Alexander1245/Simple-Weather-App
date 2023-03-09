// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(AndroidGradle.ANDROID_APPLICATION) version AndroidGradle.VERSION apply false
    id(AndroidGradle.ANDROID_LIBRARY) version AndroidGradle.VERSION apply false
    kotlin(Kotlin.ANDROID_PLUGIN) version Kotlin.VERSION apply false
    kotlin(Kotlin.KOTLIN_JVM_PLUGIN) version Kotlin.VERSION apply false
    id(AppDependencies.Hilt.PLUGIN) version AppDependencies.Hilt.VERSION apply false
}

buildscript {
    dependencies {
        classpath(AppDependencies.Navigation.ROOT_CLASSPATH)
    }
}
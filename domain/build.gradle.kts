plugins {
    id(JVM.JAVA_LIBRARY_PLUGIN)
    kotlin(Kotlin.KOTLIN_JVM_PLUGIN)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Kotlin.STDLIB)
    implementation(AppDependencies.CoreMVVM.COROUTINES)
    implementation(AppDependencies.Coroutines.CORE)
}
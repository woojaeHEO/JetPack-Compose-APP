@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.ksp.get().pluginId) version libs.versions.ksp.get()
}

android {
    namespace = "com.my.core_network"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(project(path = ":core-model"))

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.sandwich)
    implementation(libs.retrofit2)
    implementation(libs.retrofit.moshi)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.androidx.arch.core.testing)

    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}
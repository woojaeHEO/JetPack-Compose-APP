@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {
    namespace = "com.my.core_data"
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
    implementation(project(path = ":core-network"))
    implementation(project(path = ":core-database"))

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.sandwich)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.ksp.get().pluginId) version libs.versions.ksp.get()
}

android {
    namespace = "com.my.core_database"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(project(path = ":core-model"))

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    testImplementation("junit:junit:4.12")
    androidTestImplementation("junit:junit:4.12")
    ksp(libs.androidx.room.compiler)

    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}
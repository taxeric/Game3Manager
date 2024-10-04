plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.lanier.game3.manager.data"
}

dependencies {

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
plugins {
    id("com.android.library")
    id("common")
}

android {
    namespace = "pro.yakuraion.confession.domain"

    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = "1.5.5"
}

dependencies {
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    implementation(libs.compose.runtime)

    implementation(libs.gson)
}

plugins {
    id("com.android.library")
    id("common")
    id("common-ui")
}

android {
    namespace = "pro.yakuraion.confession.commonui"
}

dependencies {
    implementation(project(":domain"))
}

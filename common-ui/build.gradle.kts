plugins {
    id("com.android.library")
    id("common")
    id("common-ui")
}

android {
    namespace = "pro.yakuraion.parish.commonui"
}

dependencies {
    implementation(project(":domain"))
}

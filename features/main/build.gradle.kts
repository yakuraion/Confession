plugins {
    id("feature")
}

android {
    namespace = "pro.yakuraion.parish.main"
}

dependencies {
    implementation(project(":features:parishes"))
}

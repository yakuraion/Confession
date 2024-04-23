plugins {
    id("com.android.library")
    id("common")
}

android {
    namespace = "pro.yakuraion.parish.data"

    val roomSchemaLocation = "$projectDir/schemas/room"

    sourceSets {
        get("androidTest").assets.srcDir(roomSchemaLocation)
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.gson)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(libs.datastore)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    implementation(libs.yakuraion.android.common.network)
}

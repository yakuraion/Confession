import utils.versionCatalog

plugins {
    id("com.android.library")
    id("common")
    id("common-ui")
}

android {
    resourcePrefix(name + '_')
}

dependencies {
    add("implementation", project(":common-ui"))
    add("androidTestImplementation", project(":common-tests-ui"))

    add("implementation", project(":domain"))

    add("implementation", versionCatalog.findLibrary("destinations-compose-core").get())
    add("ksp", versionCatalog.findLibrary("destinations-compose-ksp").get())
    add("ksp", versionCatalog.findLibrary("destinations-compose-viewmodel-koin").get())
}

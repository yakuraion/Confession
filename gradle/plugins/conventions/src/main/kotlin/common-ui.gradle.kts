@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import utils.versionCatalog

configure<BaseExtension> {
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = "1.5.5"
}

configure<KotlinAndroidProjectExtension> {
    sourceSets.all {
        languageSettings.optIn("androidx.compose.material3.ExperimentalMaterial3Api")
        languageSettings.optIn("androidx.compose.ui.ExperimentalComposeUiApi")
        languageSettings.optIn("androidx.compose.foundation.layout.ExperimentalLayoutApi")
        languageSettings.optIn("androidx.compose.foundation.ExperimentalFoundationApi")
    }
}

dependencies {
    add("implementation", versionCatalog.findLibrary("androidx-core-ktx").get())
    add("implementation", versionCatalog.findLibrary("android-lifecycle").get())

    add("implementation", versionCatalog.findLibrary("koin-compose").get())

    val composeBom = platform(versionCatalog.findLibrary("compose-bom").get())
    add("implementation", composeBom)
    add("debugImplementation", composeBom)
    add("androidTestImplementation", composeBom)

    add("implementation", versionCatalog.findLibrary("compose-material3").get())
    add("implementation", versionCatalog.findLibrary("compose-tooling-preview").get())
    add("debugImplementation", versionCatalog.findLibrary("compose-tooling").get())
    add("androidTestImplementation", versionCatalog.findLibrary("compose-junit").get())
    add("debugImplementation", versionCatalog.findLibrary("compose-test-manifest").get())
    add("implementation", versionCatalog.findLibrary("compose-icons-extended").get())

    add("implementation", versionCatalog.findLibrary("compose-activity").get())
    add("implementation", versionCatalog.findLibrary("compose-viewmodel").get())
    add("implementation", versionCatalog.findLibrary("compose-constraint-layout").get())

    add("implementation", versionCatalog.findLibrary("accompanist-systemuicontroller").get())
    add("implementation", versionCatalog.findLibrary("accompanist-pager").get())

    add("implementation", versionCatalog.findLibrary("navigation-fragment").get())
    add("implementation", versionCatalog.findLibrary("navigation-ui").get())
    add("implementation", versionCatalog.findLibrary("navigation-compose").get())
    add("debugImplementation", versionCatalog.findLibrary("navigation-testing").get())

    add("androidTestImplementation", versionCatalog.findLibrary("androidx-test-core").get())
    add("androidTestImplementation", versionCatalog.findLibrary("androidx-test-core-ktx").get())
    add("androidTestImplementation", versionCatalog.findLibrary("androidx-test-junit").get())
    add("androidTestImplementation", versionCatalog.findLibrary("androidx-test-junit-ktx").get())
    add("androidTestImplementation", versionCatalog.findLibrary("androidx-test-runner").get())

    add("implementation", versionCatalog.findLibrary("coil").get())
    add("implementation", versionCatalog.findLibrary("coil-videos").get())
}

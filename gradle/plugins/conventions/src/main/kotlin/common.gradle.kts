import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import utils.configsDir
import utils.enableComposeCompilerMetrics
import utils.enableComposeCompilerReports
import utils.isCI
import utils.optIn
import utils.setStabilityConfigurationPath
import utils.versionCatalog

plugins {
    id("kotlin-android")
    id("pro.yakuraion.plugins.code-check")
}

apply(plugin = "com.google.devtools.ksp")

configure<BaseExtension> {
    compileSdkVersion(34)

    defaultConfig {
        minSdk = 24
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isDebuggable = true
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType(KotlinCompile::class.java).configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()

            optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            optIn("androidx.compose.foundation.layout.ExperimentalLayoutApi")
            optIn("androidx.compose.material3.ExperimentalMaterial3Api")

            if (project.findProperty("composeCompilerReports") == "true") {
                enableComposeCompilerReports(project.buildDir.absolutePath + "/compose_compiler")
            }
            if (project.findProperty("composeCompilerMetrics") == "true") {
                enableComposeCompilerMetrics(project.buildDir.absolutePath + "/compose_compiler")
            }

            setStabilityConfigurationPath(project.rootDir.absolutePath + "/configs/compose/stability_configuration.conf")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

configure<KotlinAndroidProjectExtension> {
    sourceSets.all {
        languageSettings.optIn("androidx.compose.material3.ExperimentalMaterial3Api")
    }
}

codeCheck {
    jvmTarget.set(JavaVersion.VERSION_17.toString())
    openFailedReport.set(!isCI)

    detekt {
        config.from(files("$configsDir/detekt/detekt.yml"))
    }
}

dependencies {
    add("coreLibraryDesugaring", versionCatalog.findLibrary("desugar-jdk-libs").get())

    add("implementation", versionCatalog.findLibrary("kotlin-coroutines").get())
    add("testImplementation", versionCatalog.findLibrary("kotlin-coroutines-test").get())

    add("implementation", versionCatalog.findLibrary("kotlin-collections-immutable").get())

    val koinBom = platform(versionCatalog.findLibrary("koin-bom").get())
    add("implementation", koinBom)
    add("testImplementation", koinBom)
    add("implementation", versionCatalog.findLibrary("koin-core").get())
    add("implementation", versionCatalog.findLibrary("koin-core-coroutines").get())
    add("testImplementation", versionCatalog.findLibrary("koin-test").get())
    add("testImplementation", versionCatalog.findLibrary("koin-test-junit4").get())
    add("ksp", versionCatalog.findLibrary("koin-ksp").get())

    add("implementation", versionCatalog.findLibrary("timber").get())

    add("testImplementation", versionCatalog.findLibrary("junit").get())
    add("testImplementation", versionCatalog.findLibrary("mockk").get())
    add("testImplementation", versionCatalog.findLibrary("robolectric").get())

    add("implementation", versionCatalog.findLibrary("yakuraion-android-common-kotlin").get())
    add("implementation", versionCatalog.findLibrary("yakuraion-android-common-coroutines").get())
    add("testImplementation", versionCatalog.findLibrary("yakuraion-android-common-coroutines-tests").get())
}

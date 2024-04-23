import org.gradle.configurationcache.extensions.capitalized
import utils.getPropertiesOrNull

plugins {
    id("com.android.application")
    id("common")
    id("common-ui")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

val customProperties = getPropertiesOrNull(File(rootDir, "custom.properties"))

android {
    namespace = "pro.yakuraion.parish"

    defaultConfig {
        applicationId = "pro.yakuraion.parish"
        versionCode = 1
        versionName = "0.1.0"

        buildConfigField(
            "String", "API_BASE_URL",
            "\"${customProperties?.get("base_url")}\""
        )
    }

    signingConfigs {
        create("dev") {
            keyAlias = "dev"
            keyPassword = "12345678"
            storeFile = file("keystores/dev-keystore.jks")
            storePassword = "12345678"
        }

        val prodPropertiesPath = "${System.getProperty("user.home")}/keystores/Parish/credentials.properties"
        getPropertiesOrNull(prodPropertiesPath)?.let { props ->
            create("prod") {
                keyAlias = props.getProperty("keyAlias")
                keyPassword = props.getProperty("keyPassword")
                storeFile = file(props.getProperty("keystorePath"))
                storePassword = props.getProperty("keystorePassword")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard/proguard-rules.pro",
                "proguard/retrofit.pro",
                "proguard/gson.pro",
            )

            resValue("string", "app_name", "Parish")
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"

            resValue("string", "app_name", "Parish (debug)")
        }
    }
    flavorDimensions += "environment"
    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"

            signingConfig = signingConfigs.findByName("dev")
        }

        create("prod") {
            dimension = "environment"

            signingConfig = signingConfigs.findByName("prod")
        }
    }

    applicationVariants.all {
        outputs.forEach { output ->
            if (output is com.android.build.gradle.internal.api.BaseVariantOutputImpl) {
                output.outputFileName = "$applicationId-v$versionName.${output.outputFile.extension}"
            }
        }
    }
}

androidComponents {
    beforeVariants { variantBuilder ->
        // Temporarily disable prod builds
        if (variantBuilder.productFlavors.containsAll(listOf("environment" to "prod"))) {
            variantBuilder.enable = false
        }
    }
}

if (System.getenv("CI") != null) {
    tasks.withType<Test> {
        exclude("**/unit/network/**")
    }
}

// ------------------------------------------------------------------------------
// Copy custom.properties into resources folder before each test task
tasks.register("copyCustomProperties", Copy::class) {
    from(rootProject.layout.projectDirectory.file("custom.properties"))
    into(layout.projectDirectory.dir("src/test/resources/"))
}

afterEvaluate {
    android.applicationVariants.forEach { variant ->
        tasks.named("test${variant.name.capitalized()}UnitTest") {
            dependsOn("copyCustomProperties")
        }
        tasks.named("process${variant.name.capitalized()}UnitTestJavaRes") {
            mustRunAfter("copyCustomProperties")
        }
    }
}
// ------------------------------------------------------------------------------

dependencies {
    implementation(project(":common-ui"))
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(project(":features:main"))
    implementation(project(":features:parishes"))

    implementation(project(":features:debugging"))
    implementation(libs.seismic)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)

    // tests
    testImplementation(libs.room.testing)
    testImplementation(libs.okhttp.mockwebserver)
    testImplementation(libs.yakuraion.android.common.mockwebserver.wrapper)
}

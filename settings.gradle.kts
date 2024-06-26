pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Confession"

include(":app")
include(":data")
include(":domain")
include(":common-ui")
include(":common-tests-ui")
include(":features:main")
include(":features:debugging")

includeBuild("gradle/plugins/conventions")

includeBuild("libs/android-common") {
    dependencySubstitution {
        substitute(module("pro.yakuraion.android-common:kotlin")).using(project(":kotlin"))
    }
    dependencySubstitution {
        substitute(module("pro.yakuraion.android-common:coroutines")).using(project(":coroutines"))
    }
    dependencySubstitution {
        substitute(module("pro.yakuraion.android-common:coroutines-tests")).using(project(":coroutines-tests"))
    }
    dependencySubstitution {
        substitute(module("pro.yakuraion.android-common:network")).using(project(":network"))
    }
    dependencySubstitution {
        substitute(module("pro.yakuraion.android-common:mockwebserver-wrapper"))
            .using(project(":mockwebserver-wrapper"))
    }
}

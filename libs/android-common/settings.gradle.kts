pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "android-common"

include(":kotlin")
include(":livedata")
include(":coroutines")
include(":coroutines-tests")
include(":network")
include(":mockwebserver-wrapper")

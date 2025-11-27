pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "LinguaLift"
include(":app")
include(":feature")
include(":core")
include(":feature:learnwords")
include(":feature:learnwords:data")
include(":feature:learnwords:domain")
include(":feature:learnwords:ui")
include(":feature:learnwords:navigation-api")
include(":feature:learnwords:navigation-impl")
include(":core-ui")

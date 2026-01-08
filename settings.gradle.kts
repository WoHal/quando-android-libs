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

    versionCatalogs {
        create("compose") {
            from(files("gradle/compose.versions.toml"))
        }
        create("ktor") {
            from(files("gradle/ktor.versions.toml"))
        }
        create("coil") {
            from(files("gradle/coil.versions.toml"))
        }
        create("koin") {
            from(files("gradle/koin.versions.toml"))
        }
    }
}

rootProject.name = "quando"
include(":preferencestore")
include(":mpvplayer")
include(":libmpv")

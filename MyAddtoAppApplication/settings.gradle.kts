pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()

        maven(url = uri("https://storage.googleapis.com/download.flutter.io"))
    }
}

rootProject.name = "My Add to App Application"
include(":app")

apply { from("flutter_settings.gradle") }

pluginManagement {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") } // fast for some regions
        maven { url = uri("https://plugins.gradle.org/m2/") }
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
        maven {
            url = uri("https://maven.neoforged.net/releases")
        }
        maven {
            url = uri("https://maven.parchmentmc.org")
        }
        maven {
            url = uri("https://repo.spongepowered.org/repository/maven-public/")
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

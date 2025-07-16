import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    java
    kotlin("jvm")  // or whatever version you're targeting
    `maven-publish`
    idea
    eclipse
}

kotlin {
    jvmToolchain(21)

    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}



idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}

repositories {
    // Forge and Maven Central included automatically
    maven {
        name = "Jared's maven"
        url = uri("https://maven.blamejared.com/")
    }
    maven {
        name = "ModMaven"
        url = uri("https://modmaven.dev")
    }
    maven {
        name = "GeckoLib"
        url = uri("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
    }
    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth"
                url = uri("https://api.modrinth.com/maven")
            }
        }
        filter {
            includeGroup("maven.modrinth")
        }
    }
}

val libs = project.versionCatalogs.find("libs")

val modId: String by project
val modName: String by project
val modAuthors: String by project
val modLicense: String by project
val modDescription: String by project
val modVersion = libs.get().findVersion("everythingjapanese").get()
val mcVersion = libs.get().findVersion("minecraft").get()
val forgeVersion = libs.get().findVersion("forge").get()
val forgeVersionRange = libs.get().findVersion("forge.range").get()
val fmlVersionRange = libs.get().findVersion("forge.fml.range").get()
val mcVersionRange = libs.get().findVersion("minecraft.range").get()

tasks.withType<Jar>().configureEach {
    from(rootProject.file("LICENSE")) {
        rename { "${it}_${modName}" }
    }

    manifest {
        attributes(mapOf(
            "Specification-Title"     to modName,
            "Specification-Vendor"    to modAuthors,
            "Specification-Version"   to modVersion,
            "Implementation-Title"    to modName,
            "Implementation-Version"  to modVersion,
            "Implementation-Vendor"   to modAuthors,
            "Built-On-Minecraft"      to mcVersion,
            "MixinConfigs"            to "$modId.mixins.json"
        ))
    }
}

tasks.withType<ProcessResources>().configureEach {
    val expandProps = mapOf(
        "minecraft_version" to mcVersion,
        "minecraft_version_range" to mcVersionRange,
        "forge_version" to forgeVersion,
        "forge_version_range" to forgeVersionRange,
        "loader_version_range" to fmlVersionRange,
        "mod_id" to modId,
        "mod_name" to modName,
        "mod_license" to modLicense,
        "mod_version" to modVersion,
        "mod_authors" to modAuthors,
        "mod_description" to modDescription,
        "terrablender_version_range" to "[0, )"
    )

    filesMatching(listOf("pack.mcmeta", "META-INF/mods.toml", "*.mixins.json")) {
        expand(expandProps)
    }

    inputs.properties(expandProps)
}

publishing {
    repositories {
        if (System.getenv("cloudUsername") == null && System.getenv("cloudPassword") == null) {
            mavenLocal()
        }
        else maven {
            name = "cloudsmith"
            url = uri("https://maven.cloudsmith.io/geckolib3/geckolib/")

            credentials {
                username = System.getenv("cloudUsername")
                password = System.getenv("cloudPassword")
            }
        }
    }
}
import net.darkhax.curseforgegradle.TaskPublishCurseForge
import net.minecraftforge.gradle.userdev.tasks.JarJar

plugins {
    id("convention")

    alias(libs.plugins.minotaur)
    alias(libs.plugins.curseforgegradle)
    alias(libs.plugins.forgegradle)
    alias(libs.plugins.mixin)
    alias(libs.plugins.parchmentforgegradle)
}

val modId: String by project
val minecraftVersion = libs.versions.minecraft.asProvider().get()
val mappingsMinecraftVersion = libs.versions.parchment.minecraft.get()
val parchmentVersion = libs.versions.parchment.asProvider().get()
val forgeVersion = libs.versions.forge.asProvider().get()
val modGroupId: String by project

version = libs.versions.everythingjapanese.get() + "-" + minecraftVersion
group = modGroupId

base {
    archivesName = modId
}

jarJar.enable()

println("Java: ${System.getProperty("java.version")}, JVM: ${System.getProperty("java.vm.version")} (${System.getProperty("java.vendor")}), Arch: ${System.getProperty("os.arch")}")

minecraft {
    mappings("official", minecraftVersion/*"${mappingsMinecraftVersion}-${parchmentVersion}-${minecraftVersion}"*/)
    //accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))

    reobf = false
    copyIdeResources = true

    /*runs {
        configureEach {
            workingDirectory = project.file("run")
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
        }

        named("client") {
            property("forge.enabledGameTestNamespaces", property("mod_id"))
        }

        named("gametest") {
            workingDirectory = project.file("run")
            environment("gametest", "true")
            args("--mixin", "--mod", "everythingjapanese", "--tests")
            mods {
                create("everythingjapanese") {
                    source(sourceSets.main.get())
                }
            }
        }

        named("server") {
            property("forge.enabledGameTestNamespaces", property("mod_id"))
            args("--nogui")
        }

        named("gameTestServer") {
            property("forge.enabledGameTestNamespaces", property("mod_id"))
        }

        named("data") {
            workingDirectory = project.file("run-data")
            args(
                "--mod", property("mod_id") as String,
                "--all",
                "--output", file("src/generated/resources/").absolutePath,
                "--existing", file("src/main/resources/").absolutePath
            )
        }
    }*/
}

// Include generated resources
sourceSets {
    getByName("main") {
        resources.srcDir("src/generated/resources")
    }
}

repositories {
    mavenLocal()
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

    maven {
        name = "Kotlin for Forge"
        url = uri("https://thedarkcolour.github.io/KotlinForForge/")
    }

}

dependencies {
    minecraft(libs.forge)

    if (System.getProperty("idea.sync.active") != "true")
        annotationProcessor(variantOf(libs.mixin) { classifier("processor") })

    //annotationProcessor("net.minecraftforge:eventbus-validator:7.0-beta.7")

    compileOnly(libs.mixinextras.common)
    annotationProcessor(libs.mixinextras.common)
    testCompileOnly(libs.mixinextras.common)

    runtimeOnly(libs.mixinextras.forge)
    jarJar(libs.mixinextras.forge) {
        jarJar.ranged(this, libs.versions.mixinextras.range.get())
    }



    implementation(libs.jopt.simple)

    compileOnly(libs.kotlinforforge)
    compileOnly(libs.kffmod)
    compileOnly(libs.kfflib)
    compileOnly(libs.kfflang)

    runtimeOnly(libs.kotlinforforge)
    runtimeOnly(libs.kffmod)
    runtimeOnly(libs.kfflib)
    runtimeOnly(libs.kfflang)

    jarJar(libs.kotlinforforge) {
        jarJar.ranged(this, libs.versions.kff.range.get())
    }
    jarJar(libs.kffmod) {
        jarJar.ranged(this, libs.versions.kff.range.get())
    }
    jarJar(libs.kfflib) {
        jarJar.ranged(this, libs.versions.kff.range.get())
    }
    jarJar(libs.kfflang) {
        jarJar.ranged(this, libs.versions.kff.range.get())
    }


    // Uncomment and add if you want those libs
    // implementation(fg.deobf("com.github.glitchfiend:TerraBlender-forge:$minecraftVersion-$terrablender_version"))
    // implementation(fg.deobf("software.bernie.geckolib:geckolib-forge-$minecraftVersion:$geckolib_version"))
}

//Make the result of the jarJar task the one with no classifier instead of no classifier and "all"
tasks.named<Jar>("jar").configure {
    archiveClassifier.set("slim")
}

tasks.named<JarJar>("jarJar").configure {
    archiveClassifier.set("")
}

mixin {
    add(sourceSets.getByName("main"), "${modId}.refmap.json")
    config("${modId}.mixins.json")
}

modrinth {
    token = System.getenv("MODRINTH_TOKEN") ?: "Invalid/No API Token Found"
    println("Modrinth: " + System.getenv("MODRINTH_TOKEN"))
    projectId = "H7XfH3TW"
    versionNumber.set(project.version.toString())
    versionName = "Everything Japanese ${project.version}"
    uploadFile.set(tasks.jarJar)
    changelog.set(rootProject.file("changelog.md").readText(Charsets.UTF_8))
    gameVersions.set(listOf(minecraftVersion))
    loaders.set(listOf("forge"))

    //https://github.com/modrinth/minotaur#available-properties
}

tasks.register<TaskPublishCurseForge>("publishToCurseForge") {
    group = "publishing"
    apiToken = System.getenv("CURSEFORGE_TOKEN") ?: "Invalid/No API Token Found"
    println("CurseForge: " + System.getenv("CURSEFORGE_TOKEN"))

    val mainFile = upload(1096258, tasks.jarJar)
    mainFile.releaseType = "release"
    mainFile.addModLoader("Forge")
    mainFile.addGameVersion(minecraftVersion)
    mainFile.addEnvironment("Client", "Server")
    mainFile.addJavaVersion("Java 21")
    mainFile.changelogType = "markdown"
    mainFile.changelog = rootProject.file("changelog.md").readText(Charsets.UTF_8)
}

publishing {
    publishing {
        publications {
            create<MavenPublication>("everythingjapanese") {
                from(components["java"])
                jarJar.component(this)
                artifactId = base.archivesName.get()
            }
        }
    }
}

tasks.named<DefaultTask>("publish").configure {
    finalizedBy("modrinth")
    finalizedBy("publishToCurseForge")
}

sourceSets.all {
    val dir = layout.buildDirectory.dir("sourcesSets/$name")
    output.setResourcesDir(dir)
    java.destinationDirectory.set(dir)
    kotlin.destinationDirectory.set(dir)
}


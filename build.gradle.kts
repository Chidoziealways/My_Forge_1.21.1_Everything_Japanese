import com.modrinth.minotaur.dependencies.DependencyType
import com.modrinth.minotaur.dependencies.ModDependency
import net.darkhax.curseforgegradle.TaskPublishCurseForge
import net.neoforged.moddevgradle.tasks.JarJar
import org.jetbrains.kotlin.gradle.utils.extendsFrom

plugins {
    id("convention")

    alias(libs.plugins.minotaur)
    alias(libs.plugins.curseforgegradle)
    alias(libs.plugins.moddevgradle)
}

// Stop ideaSync from generating run configs

//subsystems {
//    parchment {
//        minecraftVersion = "1.21.8"
//        mappingsVersion = "2025.07.20"
//    }
//}
val modId: String by project
val minecraftVersion = libs.versions.minecraft.asProvider().get()
val modGroupId: String by project

version = libs.versions.everythingjapanese.get() + "-" + minecraftVersion
group = modGroupId

val jarJarConfig by configurations.creating

configurations.getByName("implementation").extendsFrom(jarJarConfig)

base {
    archivesName = modId
}

val libraries by configurations.creating

neoForge {
    version = libs.versions.neoforge.asProvider().get()

    parchment {
        mappingsVersion = "2025.09.14"
        minecraftVersion = "1.21.8"
    }

    mods {
        create(modId) {
            sourceSet(sourceSets.main.get())
        }
    }

    validateAccessTransformers = true

    runs {
        // Custom client run
        create("customClient") {
            client() // Sets type = "client"
            gameDirectory.set(project.layout.projectDirectory.dir("runs/client"))
            systemProperty("neoforge.enableGameTest", "true")
            programArguments.addAll(listOf(
                "-mixin.config=everythingjapanese.mixins.json"
            ))
        }

        // Custom data generator run
        create("customClientData") {
            clientData() // type = "clientData"
            gameDirectory.set(project.layout.projectDirectory.dir("runs/clientData"))
            programArguments.addAll(listOf(
                "--mod", "everythingjapanese",
                "--all",
                "--output", file("src/generated/resources").absolutePath,
                "--existing", file("src/main/resources").absolutePath,
                "-mixin.config=everythingjapanese.mixins.json"
            ))
        }

        // Custom dedicated server run
        create("customServer") {
            server() // type = "server"
            gameDirectory.set(project.layout.projectDirectory.dir("runs/server"))
            programArguments.add("--nogui")
        }

        configureEach {
            // Recommended logging data for a userdev environment
            // The markers can be added/remove as needed separated by commas.
            // "SCAN": For mods scan.
            // "REGISTRIES": For firing of registry events.
            // "REGISTRYDUMP": For getting the contents of all registries.
            systemProperty("forge.logging.markers", "REGISTRIES")

            // Recommended logging level for the console
            // You can set various levels here.
            // Please read: https://stackoverflow.com/questions/2031163/when-to-use-the-different-log-levels
            logLevel = org.slf4j.event.Level.DEBUG
            additionalRuntimeClasspathConfiguration.extendsFrom(libraries)
        }
    }
}

println("Java: ${System.getProperty("java.version")}, JVM: ${System.getProperty("java.vm.version")} (${System.getProperty("java.vendor")}), Arch: ${System.getProperty("os.arch")}")

//  mappings("official", minecraftVersion/*"${mappingsMinecraftVersion}-${parchmentVersion}-${minecraftVersion}"*/)
//accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))

//minecraft {
//    accessTransformers {
//        file("src/main/resources/META-INF/accesstransformer.cfg")
//    }
//}

// Include generated resources
sourceSets {
    getByName("main") {

        resources.srcDir("src/generated/resources/$modId")
    }
}

repositories {
    mavenLocal()
    gradlePluginPortal()
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
    if (System.getProperty("idea.sync.active") != "true")
        annotationProcessor(variantOf(libs.mixin) { classifier("processor") })

    //annotationProcessor("net.minecraftforge:eventbus-validator:7.0-beta.7")


    implementation(libs.jopt.simple)

    //implementation("net.Chidoziealways.everythingkorean:everythingkorean:0.0.8-1.21.10")

    implementation("net.Chidoziealways.everythingcore:EverythingCore:6.1.0")
    // Uncomment and add if you want those libs
    // implementation(fg.deobf("com.github.glitchfiend:TerraBlender-forge:$minecraftVersion-$terrablender_version"))
    implementation("com.geckolib:geckolib-neoforge-26.2:5.5.3")
}

//Make the result of the jarJar task the one with no classifier instead of no classifier and "all"
tasks.named<Jar>("jar").configure {
    archiveClassifier.set("slim")
}

val jarJarTask by tasks.registering(JarJar::class) {
    description = "Embed Dependencies into the Mod Jar"
    group = "build"

    configuration(jarJarConfig)

    outputDirectory.set(layout.buildDirectory.dir("embed"))
}

tasks.jar {
    dependsOn(jarJarTask)
    from(jarJarTask.flatMap { it.outputDirectory })
}

modrinth {
    token = System.getenv("MODRINTH_TOKEN") ?: "Invalid/No API Token Found"
    projectId = "H7XfH3TW"
    versionNumber.set(project.version.toString())
    versionName = "Everything Japanese ${project.version}"
    versionType = "release"
    uploadFile.set(tasks.jar.flatMap { it.archiveFile })
    changelog.set(rootProject.file("changelog.md").readText(Charsets.UTF_8))
    gameVersions.set(listOf(minecraftVersion))
    dependencies.add(ModDependency("geckolib", DependencyType.REQUIRED))
    dependencies.add(ModDependency("everything-core", DependencyType.REQUIRED))
    loaders.set(listOf("neoforge"))

    //https://github.com/modrinth/minotaur#available-properties
}

tasks.register<TaskPublishCurseForge>("publishToCurseForge") {
    group = "publishing"
    apiToken = System.getenv("CURSEFORGE_TOKEN") ?: "Invalid/No API Token Found"
    doFirst {
        println("CurseForge: " + System.getenv("CURSEFORGE_TOKEN"))
    }

    val mainFile = upload(1096258, tasks.jar.flatMap { it.archiveFile })
    mainFile.releaseType = "release"
    mainFile.addModLoader("NeoForge")
    mainFile.addGameVersion(minecraftVersion)
    mainFile.addEnvironment("Client", "Server")
    mainFile.addJavaVersion("Java 22")
    mainFile.changelogType = "markdown"
    mainFile.changelog = rootProject.file("changelog.md").readText(Charsets.UTF_8)
}

publishing {
    publications {
        create<MavenPublication>("everythingjapanese") {
            from(components["java"])
            artifactId = base.archivesName.get()
        }
    }
    repositories {
        mavenLocal() // <-- local repo (~/.m2/repository)
        maven {
            url = uri("${project.buildDir}/repo")
        }
        // Or remote server
        // maven {
        //     url = uri("https://my.maven.repo/releases")
        //     credentials {
        //         username = "user"
        //         password = "pass"
        //     }
        // }
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

tasks.named<JavaCompile>("compileJava") {
    dependsOn(tasks.named("processResources"))
}
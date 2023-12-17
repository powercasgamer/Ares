plugins {
    id("common-conventions")
    id("xyz.jpenilla.run-velocity")
    id("xyz.jpenilla.gremlin-gradle")
    id("io.papermc.hangar-publish-plugin")
}

tasks {
    runVelocity {
        velocityVersion("3.3.0-SNAPSHOT")

        systemProperty("terminal.jline", false)
        systemProperty("terminal.ansi", true)
        args("-p", "25520")

        downloadPlugins {
            url("https://download.luckperms.net/1526/velocity/LuckPerms-Velocity-5.4.113.jar")
            url("https://cdn.modrinth.com/data/HQyibRsN/versions/pxgKwgNJ/MiniPlaceholders-Velocity-2.2.3.jar")
        }
    }

    shadowJar {
        relocate("org.bstats", "dev.mizule.ares.lib.org.bstats")
    }

    named("clean", Delete::class) {
        delete(project.projectDir.resolve("run"))
    }
}

// hangarPublish {
//    publications.register("plugin") {
//        version.set(project.version as String)
//        id.set("Ares")
//        channel.set(if (rootProject.versionString().endsWith("-SNAPSHOT")) "Beta" else "Release")
//        platforms {
//            register(Platforms.VELOCITY) {
//                jar.set(tasks.shadowJar.flatMap { it.archiveFile })
//                platformVersions.set(listOf("3.0"))
//                dependencies {
//                    url("LuckPerms", "https://luckperms.net") {
//                        required.set(true)
//                    }
//                }
//            }
//        }
//        pages {
//            resourcePage(provider { rootProject.file("README.md").readText() })
//        }
//    }
// }

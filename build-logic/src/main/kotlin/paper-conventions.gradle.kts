plugins {
    id("common-conventions")
    id("xyz.jpenilla.run-paper")
    id("xyz.jpenilla.gremlin-gradle")
    id("io.papermc.hangar-publish-plugin")
}

tasks {
    runServer {
        minecraftVersion("1.20.4")

        jvmArguments.add("-Dcom.mojang.eula.agree=true")
        systemProperty("terminal.jline", false)
        systemProperty("terminal.ansi", true)
        args("-p", "25519")

        downloadPlugins {
            url("https://cdn.modrinth.com/data/HQyibRsN/versions/M6gjRuIx/MiniPlaceholders-Paper-2.2.3.jar")
            url("https://download.luckperms.net/1526/bukkit/loader/LuckPerms-Bukkit-5.4.113.jar")
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
//        id.set("TimeTriggeredPerms")
//        channel.set(if (rootProject.versionString().endsWith("-SNAPSHOT")) "Beta" else "Release")
//        platforms {
//            register(Platforms.PAPER) {
//                jar.set(tasks.shadowJar.flatMap { it.archiveFile })
//                platformVersions.set(listOf("1.20", "1.19"))
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

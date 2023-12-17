import java.util.*

plugins {
    id("common-conventions")
    id("kotlin-conventions")
    id("gremlin-stuff")
    application
}

repositories {
    maven ("https://repo.flyte.gg/releases")
}

dependencies {
    runtimeDownloadOnlyApi(kotlin("stdlib"))
    runtimeDownloadOnlyApi(kotlin("reflect"))
    runtimeDownloadOnlyApi("io.github.oshai:kotlin-logging-jvm:5.1.1")
    runtimeDownloadOnlyApi("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.0")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    runtimeDownloadOnlyApi("org.spongepowered:configurate-jackson:4.2.0-SNAPSHOT")
    runtimeDownloadOnlyApi("org.spongepowered:configurate-hocon:4.2.0-SNAPSHOT")
    runtimeDownloadOnlyApi("org.spongepowered:configurate-extra-kotlin:4.2.0-SNAPSHOT")
}

applyJarMetadata("ares-app")

application {
    mainClass.set("dev.mizule.ares.app.Launcher")
}

tasks {
    clean {
        delete("run")
    }

    runShadow {
        workingDir = file("run").also(File::mkdirs)
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}
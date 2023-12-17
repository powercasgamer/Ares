import java.util.*

plugins {
    id("common-conventions")
    id("kotlin-conventions")
    id("gremlin-stuff")
    id("blossom-stuff")
    id("app-conventions")
}

dependencies {
    runtimeDownloadOnlyApi(kotlin("stdlib"))
    runtimeDownloadOnlyApi(kotlin("reflect"))
    runtimeDownloadOnlyApi(libs.slf4j.kotlin)
    api(libs.slf4j.api)
    api(libs.slf4j.simple)
    runtimeDownloadOnlyApi(libs.configurate.jackson)
    runtimeDownloadOnlyApi(libs.configurate.hocon)
    runtimeDownloadOnlyApi(libs.configurate.kotlin)
    runtimeDownloadOnlyApi(libs.jackson.kotlin)
}

tasks {
    shadowJar {
        relocateDependency(libs.slf4j.simple.get().group!!)
        relocateDependency(libs.slf4j.api.get().group!!)
    }
}

applyJarMetadata("ares-app")

application {
    mainClass.set("dev.mizule.ares.app.Launcher")
}

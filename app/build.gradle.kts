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
    runtimeDownloadOnlyApi(libs.slf4j.simple)
    runtimeDownloadOnlyApi(libs.configurate.jackson)
    runtimeDownloadOnlyApi(libs.configurate.hocon)
    runtimeDownloadOnlyApi(libs.configurate.kotlin)
    runtimeDownloadOnlyApi(libs.jackson.kotlin)
}

applyJarMetadata("ares-app")

application {
    mainClass.set("dev.mizule.ares.app.Launcher")
}

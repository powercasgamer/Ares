pluginManagement {
    includeBuild("build-logic")
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "ares-parent"

sequenceOf(
        "app"
).forEach {
    include("ares-$it")
    project(":ares-$it").projectDir = file(it)
}
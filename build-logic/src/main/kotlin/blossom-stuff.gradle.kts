plugins {
    id("common-conventions")
    id("net.kyori.blossom")
}

sourceSets {
    main {
        blossom {
            javaSources {
                property("version", project.versionString())
            }
        }
    }
}

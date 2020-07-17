buildscript {
    repositories {
        jcenter()
    }
}

plugins {
    java
    id("pl.allegro.tech.build.axion-release").version("1.12.0")
}

scmVersion {
    with(tag) {
        prefix = ""
        versionSeparator = ""
    }
    with(repository) {

    }
}

group = "com.github.gavlyukovskiy"
version = scmVersion.version

java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

tasks {
    val releaseCheck by registering {
        doLast {
            val errors = ArrayList<String>()
            if (!project.hasProperty("release.version")) {
                errors.add("'-Prelease.version' must be set")
            }
            if (System.getenv("GITHUB_ACTIONS") != "true") {
                if (!project.hasProperty("release.customUsername")) {
                    errors.add("'-Prelease.customUsername' must be set")
                }
                if (!project.hasProperty("release.customPassword")) {
                    errors.add("'-Prelease.customPassword' must be set")
                }
            }
            if (errors.isNotEmpty()) {
                throw IllegalStateException(errors.joinToString("\n"))
            }
        }
    }

    verifyRelease {
        dependsOn(releaseCheck, build)
    }
}

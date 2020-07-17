buildscript {
    repositories {
        jcenter()
    }
}

plugins {
    id("pl.allegro.tech.build.axion-release").version("1.12.0")
}

group = "com.github.gavlyukovskiy"
version = scmVersion.version

repositories {
    mavenCentral()
}

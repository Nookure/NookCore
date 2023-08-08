plugins {
    id("java")
    kotlin("jvm") version "1.9.0"
}

group = "es.angelillo15"
version = "1.0-SNAPSHOT"

allprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://oss.sonatype.org/content/repositories/central")
        maven("https://repo.dmulloy2.net/repository/public/")
        maven("https://repo.alessiodp.com/releases/")
        maven("https://papermc.io/repo/repository/maven-releases/")
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
        maven("https://mvn.exceptionflug.de/repository/exceptionflug-public/")
        maven("https://repo.simplix.dev/repository/simplixsoft-public")
        maven("https://repo.nookure.com/releases")
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    kotlin {
        jvmToolchain(17);
    }
}


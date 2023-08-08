plugins {
    id("java")
    kotlin("jvm") version "1.9.0"
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "es.angelillo15.core"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation(project(":core"))
    implementation(project(":bootstrap"))
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")

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

    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
                artifact(tasks["shadowJar"])

                groupId = "es.angelillo15.core"
                artifactId = "NookCore"
                version = rootProject.version.toString()
            }
        }
    }
}


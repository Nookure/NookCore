plugins {
    kotlin("jvm") version "1.9.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

tasks.shadowJar {
    relocate("es.angelillo15.configmanager", "es.angelillo15.core.libs.config.manager")
    relocate("org.yaml.snakeyaml", "es.angelillo15.core.libs.snakeyaml")
    relocate("org.simpleyaml", "es.angelillo15.core.libs.simpleyaml")
    relocate("es.angelillo15.glow", "es.angelillo15.core.libs.glow")
    relocate("mc.obliviate", "es.angelillo15.core.libs.obliviate")
    relocate("com.zaxxer.hikari", "es.angelillo15.core.libs.hikari")
    relocate("com.google.common", "es.angelillo15.core.libs.google.common")
    relocate("com.google.gson", "es.angelillo15.core.libs.google.gson")
    relocate("com.google.thirdparty", "es.angelillo15.core.libs.google.thirdparty")
    relocate("com.google.errorprone", "es.angelillo15.core.libs.google.errorprone")
    relocate("com.google.j2objc", "es.angelillo15.core.libs.google.j2objc")
    relocate("javax.annotation", "es.angelillo15.core.libs.javax.annotation")
    relocate("org.checkerframework", "es.angelillo15.core.libs.checkerframework")
    relocate("net.byteflux.libby", "es.angelillo15.core.libs.libby")
    relocate("ru.vyarus.yaml.updater", "es.angelillo15.core.libs.yaml-config-updater")
    relocate("kong.unirest", "es.angelillo15.core.libs.unirest")
    relocate("org.apache.http", "es.angelillo15.core.libs.apache.http")
    relocate("org.apache.commons.logging", "es.angelillo15.core.libs.commons-logging")
    relocate("org.reflections", "es.angelillo15.core.libs.reflections")
    relocate("redis.clients.jedis", "es.angelillo15.core.libs.jedis")
    relocate("io.papermc.lib", "es.angelillo15.core.libs.paperlib")
    relocate("com.github.benmanes.caffeine", "es.angelillo15.core.libs.caffeine")
    relocate("com.craftmend.storm", "es.angelillo15.core.libs.storm")
    relocate("javassist", "es.angelillo15.core.libs.javassist")
    archiveFileName.set("nookcore-core-${rootProject.version}.jar")
}

dependencies {
    implementation(libs.adventureApi)
    implementation(libs.adventureBukkit)
    implementation(libs.adventureBungee)
    implementation(libs.adventureLegacy)
    implementation(libs.caffeine)
    implementation(libs.bundles.invAPI)
    implementation(libs.configUpdater)
    implementation(libs.snakeYaml)
    implementation(libs.simpleYaml)
    implementation(libs.jedis)
    implementation(libs.hikariCP)
    implementation(libs.caffeine)
    implementation(libs.storm)
    implementation(libs.configManager)
    implementation(libs.miniMessage)
    implementation(libs.guice)
    compileOnly(libs.velocity)
    compileOnly(libs.waterfall)
    compileOnly(libs.spigot)
    annotationProcessor(libs.lombok)
    compileOnly(project(":bootstrap"))
}

kotlin {
    jvmToolchain(17);
}

publishing {
    repositories {
        maven {
            name = "nookureRepository"
            url = uri("https://repo.nookure.com/releases")
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = "es.angelillo15.core"
            artifactId = "nookcore-core"
            version = "${rootProject.version}"
            shadow.component(this)
        }
    }
}

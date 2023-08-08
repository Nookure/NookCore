plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "es.angelillo15"
version = "1.0-SNAPSHOT"

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
}

dependencies {
    api(libs.adventureApi)
    api(libs.adventureBukkit)
    api(libs.adventureBungee)
    api(libs.adventureLegacy)
    api(libs.caffeine)
    api(libs.bundles.invAPI)
    api(libs.configUpdater)
    api(libs.snakeYaml)
    api(libs.simpleYaml)
    api(libs.jedis)
    api(libs.hikariCP)
    api(libs.caffeine)
    api(libs.storm)
    api(libs.configManager)
    api(libs.miniMessage)
    api(libs.guice)
    compileOnly(libs.velocity)
    compileOnly(libs.waterfall)
    compileOnly(libs.spigot)
}

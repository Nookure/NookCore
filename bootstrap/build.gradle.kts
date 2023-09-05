plugins {
    id("net.kyori.blossom") version "1.3.1"
    `java-library`
}

dependencies {
    api(libs.libbyVelocity)
    api(libs.liblyBungee)
    api(libs.liblyBukkit)
}

blossom {
    replaceTokenIn("src/main/java/es/angelillo15/core/CoreLoader.java")
    replaceToken("{version}", "${rootProject.version}")
}

tasks.shadowJar {
    relocate("net.byteflux.libby", "es.angelillo15.core.libs.libby")
}
plugins {
    id("java")
}

group = "es.angelillo15"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(libs.libbyVelocity)
    api(libs.liblyBungee)
    api(libs.liblyBukkit)
}

val ktor_version: String by project

plugins {
    java
    kotlin("jvm") version "1.7.0"
    kotlin("plugin.serialization") version "1.5.31"
}

group = "me.jlengrand"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
//    testCompile("junit", "junit", "4.12")

    implementation("io.ktor:ktor-client-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-cio-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
}

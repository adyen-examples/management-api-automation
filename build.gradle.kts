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

    implementation("io.ktor:ktor-client-core-jvm:2.0.2")
    implementation("io.ktor:ktor-client-cio-jvm:2.0.2")
    implementation("io.ktor:ktor-client-serialization-jvm:2.0.2")
}

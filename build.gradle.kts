plugins {
    java
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.5.31"
}

group = "me.jlengrand"
version = "1.0-SNAPSHOT"
val ktorVersion = "1.6.6"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
//    testCompile("junit", "junit", "4.12")

    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
}

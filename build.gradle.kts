plugins {
    kotlin("jvm") version "1.9.23"
}

group = "org.spi3lot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("C:\\Users\\emili\\programming\\processing-4.2\\core\\library\\core.jar"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
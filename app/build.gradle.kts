plugins {
    application
    checkstyle
    id("com.github.ben-manes.versions") version "0.52.0"
    id("distribution")
    jacoco
}


group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
}

application {
    mainClass = "hexlet.code.App"
}

tasks.test {
    useJUnitPlatform()
}


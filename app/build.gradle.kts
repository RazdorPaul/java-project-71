plugins {
    id("java")
    id ("com.github.ben-manes.versions") version "0.51.0"
    application
    checkstyle
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("info.picocli:picocli:4.7.7")
    implementation(platform("com.fasterxml.jackson:jackson-bom:2.21.1"))
    implementation ("com.fasterxml.jackson.core:jackson-databind")
    implementation ("org.apache.commons:commons-collections4:4.4")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("hexlet.code.App")
}

checkstyle {
    toolVersion = "10.12.5"
    configFile = rootProject.file("config/checkstyle/checkstyle.xml")
}
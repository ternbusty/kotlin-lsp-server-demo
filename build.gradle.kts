plugins {
    kotlin("jvm") version "1.9.25"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
}

val lspVersion = "0.24.0"
val slf4jVersion = "2.0.17"

dependencies {
    implementation("org.eclipse.lsp4j:org.eclipse.lsp4j:${lspVersion}")
    implementation("org.eclipse.lsp4j:org.eclipse.lsp4j.jsonrpc:${lspVersion}")
    implementation("org.slf4j:slf4j-api:${slf4jVersion}")
}

application {
    mainClass.set("com.ternbusty.gsoc.MainKt")
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveClassifier.set("")
    }
}

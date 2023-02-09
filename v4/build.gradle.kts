import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

version = "v4"

repositories {
    mavenCentral()

    maven {
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }

    maven {
        url = uri("https://okocraft.github.io/Box/maven/")
    }
}

dependencies {
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("com.github.siroshun09.configapi:configapi-yaml:4.6.0")

    compileOnly("net.okocraft.box:api:4.4.1")
    compileOnly("net.okocraft.box:autostore:4.4.1")
    compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:24.0.0")
}

tasks.named<Copy>("processResources") {
    filesMatching("plugin.yml") {
        expand("projectVersion" to project.version)
    }
}

tasks.named<ShadowJar>("shadowJar") {
    minimize()
    archiveFileName.set("BoxMigrator-${project.version}.jar")
    relocate("com.zaxxer", "net.okocraft.boxmigrator.lib")
    relocate("com.github.siroshun09", "net.okocraft.boxmigrator.lib")
}

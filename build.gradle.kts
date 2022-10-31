plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version("7.1.2")
}

group = "net.monkeycraftmc"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
   implementation("com.velocitypowered:velocity-api:3.1.1")
   implementation("net.kyori:adventure-text-minimessage:4.11.0")
}

tasks.getByName<JavaCompile>("compileJava"){
    options.encoding = "UTF-8"
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>{
    dependencies{
        minimize()
        exclude(dependency("net.kyori:adventure-api:4.11.0"))
        exclude(dependency("com.velocitypowered:velocity-api:3.1.1"))
    }
}




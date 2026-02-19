plugins {
    java
}

group = "dev.myserver"
version = "0.1.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

repositories {
    mavenCentral()
    maven {
        name = "hytale"
        url = uri("https://maven.hytale.com/release")
    }
}

dependencies {
    implementation("com.hypixel.hytale:Server:+")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

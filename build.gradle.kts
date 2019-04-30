import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
    id("org.openjfx.javafxplugin") version "0.0.7"
}

group = "com.beyondbell"
version = "1.0.0"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

javafx {
    version = "12"
    modules = listOf("javafx.controls")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_HIGHER
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "12"
}
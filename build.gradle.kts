import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
    id("kotlinx-serialization") version "1.3.31"
    id("org.openjfx.javafxplugin") version "0.0.7"
    application
}

group = "com.beyondbell"
version = "1.0.0"

repositories {
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.1")
    implementation("io.ktor:ktor-client-apache:1.1.5")
    implementation("io.ktor:ktor-client-json:1.1.5")
    implementation("io.ktor:ktor-client-json-jvm:1.1.5")
}

javafx {
    version = "12.0.1"
    modules = listOf("javafx.fxml", "javafx.controls")
}

application {
    mainClassName = "com.beyondbell.currencyconverter.CurrencyConverterKt"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_12
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "12"
}
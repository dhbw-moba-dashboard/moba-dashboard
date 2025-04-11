
plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.sonarqube") version "5.1.0.4882"
    id("jacoco")
    application
}

group = "de.dhbw-karlsruhe.modellbahn"
version = "0.0.1"
application{
    mainClass = "de.dhbwkarlsruhe.modellbahn.ModellbahnApplication"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.boot.websocket)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.sqlite.jdbc)
    implementation(libs.hibernate.core)
    implementation(libs.hibernate.community.dialects)

    implementation(libs.commons.codec)
    implementation(libs.flyway.core)
    implementation(libs.gson)

    compileOnly(libs.lombok)

    annotationProcessor(libs.lombok)

    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.spring.boot.testcontainers)
    testImplementation(libs.spring.security.test)
    testImplementation(libs.testcontainers.junit.jupiter)

    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}
sonar {
  properties {
    property("sonar.projectKey", "dhbw-moba-dashboard_moba-dashboard")
    property("sonar.organization", "dhbw-moba-dashboard")
    property("sonar.host.url", "https://sonarcloud.io")
  }
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
    }
}

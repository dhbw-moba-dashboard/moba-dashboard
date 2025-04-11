
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
    //implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("commons-codec:commons-codec:1.18.0")
    // database driver
    implementation("org.xerial:sqlite-jdbc:3.41.2.2")
    //dialect for hibernate
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.hibernate.orm:hibernate-core")
    implementation("org.hibernate.orm:hibernate-community-dialects")
    implementation("org.flywaydb:flyway-core")
    compileOnly("org.projectlombok:lombok")

    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.testcontainers:junit-jupiter")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.google.code.gson:gson:2.11.0")
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

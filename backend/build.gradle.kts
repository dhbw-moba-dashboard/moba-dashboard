plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.sonarqube") version "5.1.0.4882"
}

group = "de.dhbw-karlsruhe.modellbahn"
version = "0.0.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
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
}

sonar {
  properties {
    property("sonar.projectKey", "dhbw-moba-dashboard_moba-dashboard")
    property("sonar.organization", "dhbw-moba-dashboard")
    property("sonar.host.url", "https://sonarcloud.io")
  }
}

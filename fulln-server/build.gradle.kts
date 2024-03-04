import com.google.protobuf.gradle.*
import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    java
    checkstyle
    idea
    id("org.springframework.boot") version "2.4.2"
    id("com.google.protobuf") version "0.8.18"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("javax.transaction:javax.transaction-api:1.3")
    runtimeOnly("mysql:mysql-connector-java")
    runtimeOnly("org.flywaydb:flyway-core")

    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
    testImplementation("org.junit.jupiter:junit-jupiter-api")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    testImplementation("org.testcontainers:testcontainers:1.16.3")
    testImplementation("org.testcontainers:mysql:1.16.3")
    annotationProcessor("org.projectlombok:lombok:1.18.20")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.20")
    testCompileOnly("org.projectlombok:lombok:1.18.20")

    // https://mvnrepository.com/artifact/io.springfox/springfox-swagger2
    implementation("io.springfox:springfox-swagger2:3.0.0")



    compileOnly("org.projectlombok:lombok:1.18.20")
    compileOnly("io.grpc:grpc-all:1.25.0")
    compileOnly("com.google.protobuf:protobuf-java:3.20.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

checkstyle {
    maxWarnings = 100;
    toolVersion = "10.0"
}

protobuf {
    protoc {
        // The artifact spec for the Protobuf Compiler
        artifact = "com.google.protobuf:protoc:3.6.1"
    }
    plugins {
        // Optional: an artifact spec for a protoc plugin, with "grpc" as
        // the identifier, which can be referred to in the "plugins"
        // container of the "generateProtoTasks" closure.
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.25.0"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                // Apply the "grpc" plugin whose spec is defined above, without options.
                id("grpc")
            }
        }
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
}



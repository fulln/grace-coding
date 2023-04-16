import org.springframework.boot.gradle.plugin.SpringBootPlugin


plugins {
    java
    idea
    id("org.springframework.boot") version "2.4.2"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("javax.transaction:javax.transaction-api:1.3")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.apache.dubbo:dubbo:2.7.21")
    // sentinel
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:2021.1")
    implementation("io.springfox:springfox-swagger2:3.0.0")

    annotationProcessor("org.projectlombok:lombok:1.18.20")

    testAnnotationProcessor("org.projectlombok:lombok:1.18.20")

    compileOnly("org.apache.logging.log4j:log4j-core:2.17.1")
    compileOnly("org.projectlombok:lombok:1.18.20")

    testCompileOnly("org.projectlombok:lombok:1.18.20")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
plugins {
	kotlin("jvm") version "2.0.0"
	kotlin("plugin.spring") version "2.0.0"
	id("org.springframework.boot") version "3.5.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.asciidoctor.jvm.convert") version "3.3.2"
	kotlin("plugin.jpa") version "2.0.0"
}

group = "jyk"
version = "0.0.1"
description = "jyk-server for multi purpose"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")

dependencies {
    // Spring MVC, Spring Boot
	implementation("org.springframework.boot:spring-boot-starter-web")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    // 코틀린 리플렉션을 위해 필요
	implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Devtools
	developmentOnly("org.springframework.boot:spring-boot-devtools")

    // DB
//	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	runtimeOnly("com.mysql:mysql-connector-j")


    // Test
    // Spring 환경에서의 단위 테스트, 통합 테스트, Mock 테스트를 모두 지원하는 "기본 스타터"
	testImplementation("org.springframework.boot:spring-boot-starter-test")
    // 코루틴 테스트 지원을 위해 필요
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")

}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	outputs.dir(project.extra["snippetsDir"]!!)
}

tasks.asciidoctor {
	inputs.dir(project.extra["snippetsDir"]!!)
	dependsOn(tasks.test)
}

plugins {
	java
	id("org.springframework.boot") version "3.5.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("info.solidsoft.pitest") version "1.15.0"
}

group = "br.com.calculator"
version = "0.0.1-SNAPSHOT"
description = "Testing with Pitest"

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
	implementation("org.springframework.boot:spring-boot-starter")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	//pitest
	testImplementation(platform("org.junit:junit-bom:5.10.2")) // 📦 Define a versão única do JUnit 5 para todas as libs (evita conflitos).
	testImplementation("org.junit.jupiter:junit-jupiter") // ⚡ Inclui a engine e API do JUnit 5 → permite escrever e rodar testes modernos.
	testRuntimeOnly("org.pitest:pitest-junit5-plugin:1.2.1") // 🧬 Faz a ponte entre PITEST e JUnit 5 → permite rodar mutation testing nos seus testes.
}

tasks.withType<Test> {
	useJUnitPlatform()
}


pitest {
	junit5PluginVersion.set("1.2.1") // 🔌 Versão do plugin que integra o JUnit 5 ao PITEST.
	pitestVersion.set("1.16.0") // 📦 Versão principal do PITEST usada no projeto.
	targetClasses.set(listOf("br.com.calculator.*")) // 🎯 Classes que serão mutadas.
	targetTests.set(listOf("br.com.calculator.*")) // 🧪 Classes de teste que serão executadas contra os mutantes.
	mutators.set(listOf("DEFAULTS")) // 🧬 Define quais tipos de mutações serão aplicadas (padrões do PITEST).
	threads.set(4) // ⚡ Número de threads paralelas para acelerar a execução.
	outputFormats.set(setOf("HTML", "XML")) // 📊 Formatos de saída dos relatórios de mutação.
	mutationThreshold.set(80) // ✅ Percentual mínimo de mutantes que precisam ser mortos (meta de qualidade).
	excludedClasses.set(listOf("*Config", "*Application")) // 🚫 Classes que não devem sofrer mutação (ex: config, main).
	excludedMethods.set(listOf("toString", "equals", "hashCode")) // 🛑 Métodos ignorados (geralmente não impactam lógica de negócio).
	failWhenNoMutations.set(false) // 🤷 Se não houver mutações, não falha a execução.
}
plugins {
    kotlin("jvm") version "1.4.10"
    `maven-publish`
}

group = "com.claygillman"
version = "1.0.3"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

val sourcesJar = tasks.create<Jar>("sourcesJar") {
    dependsOn(tasks.classes)
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {

        create<MavenPublication>("kBehaviorTree") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifactId = "kbehaviortree"
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/clay07g/kBehaviorTree")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

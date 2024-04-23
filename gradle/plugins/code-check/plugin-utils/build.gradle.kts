plugins {
    `kotlin-dsl`
    `maven-publish`
}

group = "pro.yakuraion.plugins.utils"
version = "1.0-SNAPSHOT"

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

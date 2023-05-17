
project.group = group
project.version = version


plugins {
    `kotlin-dsl`
    `maven-publish`
    `version-catalog`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal() // so that external plugins can be resolved in dependencies section
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/fpitpit/pitdev-build-logic")
            credentials {
                username =
                    project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USERNAME")
                password =
                    project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }

    }
    publications {
        register<MavenPublication>(project.name) {
            artifactId = project.name
            logger.quiet("artifactId = $artifactId")
            from(components["java"])
        }
    }
    publications {
        register<MavenPublication>("PitDevVersionCatalog") {
            artifactId = "version-catalog"
            artifact("../../gradle/pitdev.versions.toml")
        }
    }
}


dependencies {
    api(libs.android.tools.build.gradle.api)
    api(libs.jetbrains.kotlin.gradle.plugin)
    api(libs.android.tools.build.gradle)
    gradleApi()
}

gradlePlugin {
    // register JacocoReportsPlugin as a plugin
    plugins {
        register("jacoco-reports") {
            id = "jacoco-reports"
            implementationClass = "fr.pitdev.plugins.JacocoReportsPlugin"
        }
    }
}



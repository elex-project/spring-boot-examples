buildscript {
    repositories {
        maven {
            url = uri("https://repository.elex-project.com/repository/maven")
        }
    }

    dependencies {
        classpath ("com.jaredsburrows:gradle-license-plugin:0.8.90")
    }
}
plugins {
    id("com.github.ben-manes.versions") version "0.39.0"
}

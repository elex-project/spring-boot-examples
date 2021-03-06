/*
 * Examples for Spring-boot
 *
 * Copyright (c) 2021-2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

buildscript {
    repositories {
        maven {
            setUrl("https://repository.elex-project.com/repository/maven")
        }
    }
    dependencies {
        classpath ("com.jaredsburrows:gradle-license-plugin:0.8.90")
    }
}
plugins {
    id("com.github.ben-manes.versions") version "0.39.0"
}

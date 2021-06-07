buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
        classpath("com.android.tools.build:gradle:4.2.1")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.5.0")
        classpath("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")
        classpath("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0")
    }
}

group = "me.eduardo"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}
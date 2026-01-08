import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.maven.publish)
}

val libVersion = "0.0.2"

android {
    namespace = "io.github.wohal.quando.preference"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_11)
    }
}

dependencies {
    implementation(platform(compose.androidx.compose.bom))
    implementation(compose.androidx.appcompat)
    implementation(compose.bundles.common)
    implementation(libs.preferences)
    implementation(libs.kotlinx.coroutines.android)
}

afterEvaluate {
    mavenPublishing {
        publishToMavenCentral(true)
        signAllPublications()
        coordinates(
            "io.github.wohal",
            "quando-preference",
            libVersion
        )

        pom {
            name = "Android shared preferences"
            description = "The android shared preferences library."
            inceptionYear = "2025"
            url = "https://github.com/WoHal/quando-android-libs/"
            licenses {
                license {
                    name = "MIT License"
                    url = "https://opensource.org/license/mit/"
                    distribution = "repo"
                }
            }
            developers {
                developer {
                    id = "WoHal"
                    name = "WoHal"
                    url = "https://github.com/WoHal/"
                }
            }
            scm {
                url = "https://github.com/WoHal/quando-android-libs/"
                connection = "scm:git:git://github.com/WoHal/quando-android-libs.git"
                developerConnection = "scm:git:ssh://git@github.com/WoHal/quando-android-libs.git"
            }
        }
    }
}
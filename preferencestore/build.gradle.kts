import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
//    alias(libs.plugins.maven.publish)
    id("maven-publish")
}

android {
    namespace = "wang.soian.preferencestore"
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

    publishing {
        singleVariant("release") {
            withSourcesJar()
//            withJavadocJar()
        }
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
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "wang.soian.quando"
                artifactId = "preferencestore"
                version = "0.0.1"
            }
        }

        repositories {
            maven {
                name = "GithubPackages"
                url = uri("https://maven.pkg.github.com/WoHal/quando-android")
                credentials {
                    username = project.findProperty("gpr.user") as? String ?: System.getenv("USERNAME")
                    password = project.findProperty("gpr.key") as? String ?: System.getenv("ACCESS_TOKEN")
                }
            }
        }
    }
}

//afterEvaluate {
//    mavenPublishing {
//        publishToMavenCentral(true)
//        signAllPublications()
//        coordinates(
//            "wang.soian.quando",
//            "preferences-lib",
//            version as String?
//        )
//
//        pom {
//            name = "Android shared preferences"
//            description = "The android shared preferences library."
//            inceptionYear = "2025"
//            url = "https://github.com/WoHal/quando-android/"
//            licenses {
//                license {
//                    name = "MIT License"
//                    url = "https://opensource.org/license/mit/"
//                    distribution = "repo"
//                }
//            }
//            developers {
//                developer {
//                    id = "WoHal"
//                    name = "WoHal"
//                    url = "https://github.com/WoHal/"
//                }
//            }
//            scm {
//                url = "https://github.com/WoHal/quando-android/"
//                connection = "scm:git:git://github.com/WoHal/quando-android.git"
//                developerConnection = "scm:git:ssh://git@github.com/WoHal/quando-android.git"
//            }
//        }
//    }
//}
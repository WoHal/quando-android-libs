plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "wang.soian.quando"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "wang.soian.quando"
        minSdk = 21
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

dependencies {
    implementation(compose.bundles.common)
//    implementation(compose.libs.androidx.core.ktx)
//    implementation(compose.libs.androidx.appcompat)
//    implementation(compose.libs.material)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
}
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.shigure.check24"
    compileSdk = 33

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.shigure.check24"
        minSdk = 24
        targetSdk = 33
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
            buildConfigField ("String", "URL_COUNTRIES", "\"https://restcountries.com/v3.1/\"")
        }
        debug {
            buildConfigField ("String", "URL_COUNTRIES", "\"https://restcountries.com/v3.1/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = libs.versions.compose.kotlinCompiler.get()
}

dependencies {

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.ui)
    implementation(libs.compose.runtime)
    implementation(libs.compose.material3)
    implementation(libs.compose.preview)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.moshiConverter)
    implementation(libs.coil.compose)
    implementation(libs.androidx.navigation)

    implementation(libs.room.runtime)
    implementation(libs.androidx.junit.ktx)

    implementation(libs.moshi.core)
    implementation(libs.moshi.adapters)
    kapt(libs.moshi.annotationProcessor)

    implementation(libs.androidx.junit.ktx)
    kapt(libs.room.kapt)
    annotationProcessor(libs.room.kapt)
    implementation(libs.room.coroutines)

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit)
}

fun param(name: String): String = gradleLocalProperties(file("${rootProject.rootDir}")).getProperty(name)
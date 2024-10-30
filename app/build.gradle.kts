import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.jackrjie.omdbmovie"
    compileSdk = 34

    val mProperties = Properties()
    mProperties.load(project.rootProject.file("local.properties").inputStream())

    defaultConfig {
        applicationId = "com.jackrjie.omdbmovie"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            isDebuggable = true
            buildConfigField("String","BASE_URL", "\"https://www.omdbapi.com/\"")
            buildConfigField("String","API_KEY", mProperties.getProperty("API_KEY"))
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String","BASE_URL", "\"https://www.omdbapi.com/\"")
            buildConfigField("String","API_KEY", mProperties.getProperty("API_KEY"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.bundles.androidx.bundle)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.bundles.hilt.bundle)
    implementation(libs.bundles.retrofit.bundle)
    implementation(libs.bundles.room.bundle)
    implementation(libs.bundles.coil.bundle)

    kapt(libs.androidx.hilt.compiler)
    kapt(libs.androidx.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
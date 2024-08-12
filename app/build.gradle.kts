plugins {
    alias(libs.plugins.android)
    alias(libs.plugins.kotlinAndroid)
}


android {
    compileSdk = 34

    namespace = "cameralib.demo"

    defaultConfig {
        applicationId = "cameralib.demo"
        minSdk = 24
        targetSdk = 34
        versionName = "1.0"
        versionCode = 1
        vectorDrawables.useSupportLibrary = true
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }
}

dependencies {
    implementation(project(":cameralib"))
}

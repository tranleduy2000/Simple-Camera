plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    namespace = "cameralib"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        resourcePrefix = "camlib_"
    }

    buildTypes {
        debug {

        }
        release {
        }
    }

    sourceSets {
        getByName("main").res.srcDirs("src/main/res-common")
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    lint {
        abortOnError = true
        disable += "MissingTranslation"
    }
}

dependencies {

    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.material)
    api(libs.bundles.androidx.camera)
    api(libs.androidx.documentfile)
    api(libs.androidx.exifinterface)
    api(libs.androidx.lifecycle.runtime.ktx)
    api(libs.androidx.window)
    api(libs.glide)

}

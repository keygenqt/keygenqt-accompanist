plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.diffplug.spotless")
}

version = "0.0.14"
group = "com.keygenqt.accompanist"

spotless {
    kotlin {
        target("**/*.kt")
        licenseHeaderFile("$buildDir/../LICENSE")
    }
}

android {

    compileSdk = 33

    defaultConfig {
        minSdk = 23
        targetSdk = 33
        setProperty("archivesBaseName", "keygenqt-accompanist-$version")
    }

    composeOptions {
        kotlinCompilerExtensionVersion = findProperty("composeCompilerVersion").toString()
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("androidx.compose.ui:ui:1.1.0-rc01")
    implementation("androidx.compose.material:material:1.1.0-rc01")
    implementation("androidx.paging:paging-compose:1.0.0-alpha14")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.22.0-rc")
}
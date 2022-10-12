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

// https://developer.android.com/jetpack/androidx/releases/compose
val composeVersion = "1.2.1"
// https://google.github.io/accompanist/
val accompanistVersion = "0.26.5-rc"
// https://developer.android.com/jetpack/androidx/releases/paging
val pagingComposeVersion = "1.0.0-alpha16"
// https://developer.android.com/jetpack/androidx/releases/compose-material3
val material3Version = "1.0.0-rc01"

dependencies {
    implementation("androidx.compose.material3:material3:$material3Version")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.paging:paging-compose:$pagingComposeVersion")
    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanistVersion")
}
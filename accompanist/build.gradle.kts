plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish`
    id("com.jfrog.artifactory")
}

// dependencies versions
val composeVersion: String by project
val googleAccompanistVersion: String by project
val pagingComposeVersion: String by project
val modifierVersion: String by project

// lib info
val libVersion: String by project
val libGroup: String by project

publishing {
    publications {
        register("aar", MavenPublication::class) {
            version = libVersion
            groupId = libGroup
            artifactId = project.name
            artifact("$buildDir/outputs/aar/surf-accompanist-$libVersion-release.aar")
        }
    }
}

artifactory {
    setContextUrl("https://artifactory.surfstudio.ru/artifactory")
    publish {
        repository {
            setRepoKey("libs-release-local")
            setUsername(System.getenv("surf_maven_username"))
            setPassword(System.getenv("surf_maven_password"))
        }
        defaults {
            publications("aar")
            setPublishArtifacts(true)
        }
    }
}

android {

    compileSdk = 30

    defaultConfig {
        minSdk = 23
        targetSdk = 31
        setProperty("archivesBaseName", "surf-accompanist-$libVersion")
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {

    implementation("ru.surfstudio.compose:modifier-ext:0.0.12")

    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.paging:paging-compose:$pagingComposeVersion")
    implementation("androidx.navigation:navigation-compose:2.4.0-rc01")

    implementation("androidx.fragment:fragment-ktx:1.4.0")

    implementation("com.google.accompanist:accompanist-insets:$googleAccompanistVersion")
    implementation("com.google.accompanist:accompanist-pager:$googleAccompanistVersion")
    implementation("com.google.accompanist:accompanist-swiperefresh:$googleAccompanistVersion")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$googleAccompanistVersion")
}
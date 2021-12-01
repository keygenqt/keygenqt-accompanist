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
version = "0.0.10"
group = "ru.surfstudio.compose"

publishing {
    publications {
        register("aar", MavenPublication::class) {
            groupId = group.toString()
            artifactId = project.name
            artifact("$buildDir/outputs/aar/accompanist-$version-release.aar")
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
        setProperty("archivesBaseName", "surf-accompanist-$version")
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

    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")

    implementation("androidx.paging:paging-compose:$pagingComposeVersion")

    implementation("com.google.accompanist:accompanist-insets:$googleAccompanistVersion")
    implementation("com.google.accompanist:accompanist-pager:$googleAccompanistVersion")
    implementation("com.google.accompanist:accompanist-swiperefresh:$googleAccompanistVersion")

    implementation("ru.surfstudio.compose:modifier-ext:$modifierVersion")
}
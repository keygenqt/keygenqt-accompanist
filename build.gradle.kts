plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish`
    id("com.jfrog.artifactory")
    id("com.diffplug.spotless")
}

val compose = "1.0.2"

version = "0.0.9"
group = "com.keygenqt.accompanist"

spotless {
    kotlin {
        target("**/*.kt")
        licenseHeaderFile("$buildDir/../LICENSE")
    }
}

publishing {
    publications {
        register("aar", MavenPublication::class) {
            groupId = group.toString()
            artifactId = project.name
            artifact("$buildDir/outputs/aar/surf-accompanist-$version-debug.aar")
        }
    }
}

artifactory {
    setContextUrl("https://artifactory.keygenqt.com/artifactory")
    publish {
        repository {
            setRepoKey("open-source")
            setUsername(findProperty("arusername").toString())
            setPassword(findProperty("arpassword").toString())
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
        kotlinCompilerExtensionVersion = compose
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
}

dependencies {

    implementation("androidx.compose.ui:ui:$compose")
    implementation("androidx.compose.material:material:$compose")

    val pagingCompose = "1.0.0-alpha12"
    implementation("androidx.paging:paging-compose:$pagingCompose")

    val accompanist = "0.17.0"
    implementation("com.google.accompanist:accompanist-insets:$accompanist")
    implementation("com.google.accompanist:accompanist-pager:$accompanist")
    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanist")

    val modifier = "0.0.2"
    implementation("com.keygenqt.modifier:compose-modifier-ext:$modifier")
}
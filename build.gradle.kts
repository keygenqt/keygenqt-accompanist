plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish`
    id("com.jfrog.artifactory")
    id("com.diffplug.spotless")
}

val composeVersion: String = findProperty("composeVersion") as? String ?: "1.1.0-rc01"
val accompanistVersion: String = findProperty("accompanistVersion") as? String ?: "0.22.0-rc"
val pagingComposeVersion: String = findProperty("pagingComposeVersion") as? String ?: "1.0.0-alpha14"

version = "0.0.14"
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
            artifact("$buildDir/outputs/aar/keygenqt-accompanist-$version-debug.aar")
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
        setProperty("archivesBaseName", "keygenqt-accompanist-$version")
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.paging:paging-compose:$pagingComposeVersion")
    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanistVersion")
}
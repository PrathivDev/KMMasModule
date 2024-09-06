import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    id("com.vanniktech.maven.publish") version "0.28.0"

}

android {
    namespace = "io.github.prathivdev"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)

    debugImplementation(libs.compose.ui.tooling)

    // 3rd party

    implementation(libs.coil.compose)
}



//afterEvaluate {
//    publishing {
//        publications {
//            register<MavenPublication>("release") {
//                groupId = "com.github.Prathiv07"
//                artifactId = "KMMasModule"
//                version = "4.2"
//                from(components["release"])
//            }
//        }
//    }
//}


mavenPublishing {
    coordinates(
        groupId = "io.github.prathivdev",
        artifactId = "KMMasModule",
        version = "1.0.0"
    )
    pom {
        name.set("Sample KMM UI")
        description.set("Sample KMM project as library includes both UI and shared Module")
        inceptionYear.set("2024")
        url.set("https://github.com/PrathivDev/KMMasModule")

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        // Specify developers information
        developers {
            developer {
                id.set("io.github.prathivdev")
                name.set("Prathiv A R")
                email.set("arprathiv7@gmail.com")
            }
        }
        scm {
            url.set("https://github.com/PrathivDev/KMMasModule")
        }
    }

    // Configure publishing to Maven Central
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    // Enable GPG signing for all publications
    signAllPublications()
}
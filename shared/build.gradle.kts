import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("com.vanniktech.maven.publish") version "0.28.0"

}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "io.github.prathivdev"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

//afterEvaluate {
//    publishing {
//        publications {
//            register<MavenPublication>("release") {
//                groupId = "com.github.Prathiv07"
//                artifactId = "shared"
//                version = "4.2"
//                from(components["kotlin"])
//            }
//
//        }
//    }
//}


mavenPublishing {
    coordinates(
        groupId = "io.github.prathivdev",
        artifactId = "shared",
        version = "1.0.0"
    )
    pom {
        name.set("Sample KMM")
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
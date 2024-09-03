
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
    `maven-publish`
}

android {
    namespace = "com.kobil.kmmasmodule.android"
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



afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                groupId = "com.github.Prathiv07"
                artifactId = "KMMasModule"
                version = "2.4"

                afterEvaluate {
                    from(components["release"])
                }

                pom.withXml {
                    asNode().appendNode("dependencies").apply {
                        appendNode("dependency").apply {
                            appendNode("groupId", "com.github.Prathiv07")
                            appendNode("artifactId", "shared")
                            appendNode("version", "2.4")
                            appendNode("scope", "compile")
                        }
                    }
                }
            }
        }
    }
}
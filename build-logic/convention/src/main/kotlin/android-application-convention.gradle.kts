import fr.pitdev.config.ProjectConfig
import fr.pitdev.config.libs

private val libs = project.libs

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = ProjectConfig.AndroidConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.AndroidConfig.minSdk
        targetSdk = ProjectConfig.AndroidConfig.targetSdk
        versionCode = ProjectConfig.AndroidConfig.versionCode
        versionName = ProjectConfig.AndroidConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        android {
            buildTypes {
                debug {
                    enableUnitTestCoverage = true
                }
            }
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = ProjectConfig.AndroidConfig.javaVersion
        targetCompatibility = ProjectConfig.AndroidConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = ProjectConfig.AndroidConfig.kotlinJvmVersion
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

}

dependencies {

    implementation(libs.findLibrary("androidx.core.ktx").get())
    implementation(libs.findBundle("androidx.lifecycle").get())

    testImplementation(libs.findLibrary("junit").get())
    testImplementation(libs.findLibrary("robolectric").get())
    testImplementation(libs.findLibrary("jetbrains.kotlinx.coroutines.test").get())

    androidTestImplementation(libs.findLibrary("androidx.test.ext.junit").get())
    androidTestImplementation(libs.findLibrary("androidx.test.espresso.core").get())

}

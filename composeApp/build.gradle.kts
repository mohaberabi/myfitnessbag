import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.klib)

}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
        iosTarget.compilations {
            val main by getting {
                cinterops {
                    create("helper")
                }
            }
        }
    }



    sourceSets {

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.crypto.securirty)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)

        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.navigation.compose)
            implementation(libs.bundles.ktor)
            implementation(libs.datastore.prefs)
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)
            implementation(libs.coil.compose.core)
            implementation(libs.coil.mutliplatform)
            implementation(libs.coil.network.ktor)
            implementation(libs.coil.compose)

        }
    }
}

android {
    namespace = "com.myfitnessbag.order"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.myfitnessbag.order"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }

    androidResources {
        generateLocaleConfig = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}


tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

dependencies {
    add("kspAndroid", libs.room.compiler)
    add("kspCommonMainMetadata", libs.room.compiler)
}
room {
    schemaDirectory("$projectDir/schemas")
}
swiftklib {
    create("helper") {
        packageName = ("com.myfitnessbag.order")
        path = file("../iosApp/iosApp/helper")
    }
}
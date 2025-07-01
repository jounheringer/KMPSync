import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.ktorfit)
    alias(libs.plugins.kotlin.serialization)
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

kotlin {
    androidTarget()
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
            linkerOpts.add("-lsqlite3")
            freeCompilerArgs += listOf(
                "-Xbinary=bundleId=com.reringuy.sync",
                "-Xexport-kdoc",
            )
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.ktor.client.android)

        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.annotations)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)
            implementation(libs.ktorfit.lib)
            implementation(libs.ktor.serialization.json)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.ktor.client.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        iosMain.dependencies {
            implementation(kotlin("stdlib"))
            implementation(libs.koin.core)
            implementation(libs.ktor.client.darwin)
        }
    }

    targets.configureEach {
        compilations.configureEach {
            compileTaskProvider.get().compilerOptions {
                freeCompilerArgs.addAll(
                    "-Xexpect-actual-classes",
                    "-opt-in=kotlin.time.ExperimentalTime"
                )
            }
        }
    }
}

ksp {
    arg("koin.generated", "true")
    arg("KOIN_DEFAULT_MODULE","true")
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspCommonMainMetadata", libs.ksp.compiler)

    add("kspAndroid", libs.ktorfit.ksp)
    add("kspAndroid", libs.room.compiler)

    add("kspIosSimulatorArm64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.ktorfit.ksp)

    add("kspIosX64", libs.room.compiler)
    add("kspIosX64", libs.ktorfit.ksp)

    add("kspIosArm64", libs.room.compiler)
    add("kspIosArm64", libs.ktorfit.ksp)
}

android {
    namespace = "com.reringuy.sync"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.reringuy.sync"
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

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            aaptOptions.cruncherEnabled = false
        }
        release {
            isMinifyEnabled = false
            aaptOptions.cruncherEnabled = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

tasks.withType<KotlinCompilationTask<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        freeCompilerArgs.addAll(
            "-opt-in=kotlin.time.ExperimentalTime",
            "-Xprint-constructor-signatures"
        )
    }
}

tasks.matching {
    it.name in listOf(
        "linkReleaseFrameworkIosArm64",
        "linkReleaseFrameworkIosSimulatorArm64",
        "createReleaseApkListingFileRedirect",
        "linkReleaseFrameworkIosX64",
        "packageRelease"
    )
}.configureEach {
    onlyIf { gradle.startParameter.taskNames.any { it.contains("Release", ignoreCase = true) } }
}

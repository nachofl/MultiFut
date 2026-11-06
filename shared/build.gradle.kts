import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
  alias(libs.plugins.sqlDelight)

	alias(libs.plugins.kotlinMultiplatform)
	alias(libs.plugins.androidLibrary)
	id("org.jetbrains.kotlin.plugin.serialization") version libs.versions.kotlin.get()
}

kotlin {

    sqldelight {
        databases {
            create("AppDatabase") {
                packageName.set("com.multifut.shared.db")
            }
        }
    }

	androidTarget {
		compilations.all {
			compileTaskProvider.configure {
				compilerOptions {
					jvmTarget.set(JvmTarget.JVM_1_8)
				}
			}
		}
	}

	targets.all {
		compilations.all {
			compileTaskProvider.configure {
				compilerOptions {
					languageVersion.set(KotlinVersion.KOTLIN_2_1)
					apiVersion.set(KotlinVersion.KOTLIN_2_1)
					freeCompilerArgs.add("-Xexpect-actual-classes")
					optIn.add("kotlin.time.ExperimentalTime")
				}
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
			implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
			implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
			implementation(libs.koin.core)
implementation(libs.sqlDelight.runtime)

		}
		commonTest.dependencies {
			implementation(libs.kotlin.test)
		}
		androidMain.dependencies {
			implementation(libs.sqlDelight.driver.android)
			implementation("com.google.firebase:firebase-auth:24.0.1")
			implementation("com.google.firebase:firebase-firestore:26.0.2")
			implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.10.2")
		}
		iosMain.dependencies {
			implementation(libs.sqlDelight.driver.native)
		}
	}
}

android {
	namespace = "com.multifut"
	compileSdk = 35
	defaultConfig {
		minSdk = 24
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
}


plugins {
	kotlin("multiplatform")
	id("com.android.library")
	alias(libs.plugins.jetbrains.compose)
	id("org.jetbrains.kotlin.plugin.compose")
}
kotlin {
	androidTarget()

	iosX64()
	iosArm64()
	iosSimulatorArm64()

	sourceSets {
		val commonMain by getting {
			dependencies {
				implementation(project(":shared"))
				// Las versiones de Compose las gestiona el plugin de Compose Multiplatform.
				implementation(compose.runtime)
				implementation(compose.foundation)
				implementation(compose.material)
				implementation(compose.ui)
				@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
				implementation(compose.components.resources)
			}
		}

		val commonTest by getting {
			dependencies {
				implementation(kotlin("test"))
			}
		}

		val androidMain by getting {
			dependencies {

			}
		}
	}
}

android {
	namespace = "com.multifut.sharedui"
	compileSdk = 35

	defaultConfig {
		minSdk = 24
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
}
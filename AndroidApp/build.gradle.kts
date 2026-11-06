plugins {
	alias(libs.plugins.androidApplication)
	alias(libs.plugins.kotlinAndroid)
	alias(libs.plugins.compose.compiler)
	id("com.google.gms.google-services")
}

android {
	namespace = "com.multifut.android"
	compileSdk = 35
	defaultConfig {
		applicationId = "com.multifut.android"
		minSdk = 24
		targetSdk = 35
		versionCode = 1
		versionName = "1.0"
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
}

kotlin {
	compilerOptions {
		jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
	}
}

dependencies {
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.8.1")
	implementation(platform("com.google.firebase:firebase-bom:34.5.0"))
	implementation("com.google.firebase:firebase-firestore")
	implementation("com.google.firebase:firebase-auth")
	implementation("com.google.firebase:firebase-analytics")
	implementation(projects.shared)
	implementation(libs.compose.ui)
	implementation(libs.compose.ui.tooling.preview)
	implementation(libs.compose.material3)
	implementation(libs.androidx.activity.compose)
	implementation(libs.koin.android)
implementation(libs.sqlDelight.driver.android)
	debugImplementation(libs.compose.ui.tooling)
}
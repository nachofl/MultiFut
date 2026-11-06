plugins {
    id("io.gitlab.arturbosch.detekt") version "1.23.6" apply false
    id("com.github.spotbugs") version "6.5.0" apply false

    id("com.github.ben-manes.versions") version "0.51.0" apply true

	alias(libs.plugins.androidApplication).apply(false)
	alias(libs.plugins.androidLibrary).apply(false)
	alias(libs.plugins.kotlinAndroid).apply(false)
	alias(libs.plugins.kotlinMultiplatform).apply(false)
	alias(libs.plugins.compose.compiler).apply(false)

	id("com.google.gms.google-services") version "4.4.4" apply false
	alias(libs.plugins.android.kotlin.multiplatform.library).apply(false)
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "com.github.spotbugs")

    apply(from = rootProject.file("detekt.gradle"))
    apply(from = rootProject.file("spotbugs.gradle"))
}

package data.enums

import kotlinx.serialization.Serializable

@Serializable
enum class Language(val code: String, val displayName: String) {
	ENGLISH("en", "English"),
	SPANISH("es", "Español"),
	GALICIAN("gl", "Galego");

	companion object {
		fun fromCode(code: String): Language? {
			return values().find { it.code == code }
		}
	}
}
package data.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Language(val code: String, val displayName: String) {
	@SerialName("en")
	ENGLISH("en", "english"),

	@SerialName("es")
	SPANISH("es", "spanish"),

	@SerialName("gl")
	GALICIAN("gl", "galician");

	companion object {
		private val BY_CODE: Map<String, Language> = values().associateBy { it.code }

		fun fromCode(code: String): Language? = BY_CODE[code]
	}
}

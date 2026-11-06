package data.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Position(val displayName: String) {
	@SerialName("goalkeeper")
	GOALKEEPER("goalkeeper"),

	@SerialName("defender")
	DEFENDER("defender"),

	@SerialName("midfielder")
	MIDFIELDER("midfielder"),

	@SerialName("forward")
	FORWARD("forward");

	companion object {
		private val BY_DISPLAY: Map<String, Position> = values().associateBy { it.displayName }

		fun fromDisplayName(displayName: String): Position? = BY_DISPLAY[displayName]
	}
}

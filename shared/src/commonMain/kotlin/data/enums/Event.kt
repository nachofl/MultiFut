package data.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class EventType(val displayName: String) {
	@SerialName("goal")
	GOAL("goal"),

	@SerialName("yellowCard")
	YELLOW_CARD("yellowCard"),

	@SerialName("redCard")
	RED_CARD("redCard")
}

@Serializable
data class Event(
	val minute: Int,
	val type: EventType,
	val playerId: String,
	val teamId: String? = null
) {
	init {
		require(minute in MINUTE_MIN..MINUTE_MAX) {
			"Minute must be between $MINUTE_MIN and $MINUTE_MAX"
		}
	}

	private companion object {
		const val MINUTE_MIN = 0
		const val MINUTE_MAX = 90
	}
}

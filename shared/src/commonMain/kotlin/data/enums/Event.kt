package data.enums

import kotlinx.serialization.Serializable

@Serializable
enum class EventType(val displayName: String) {
	GOAL("goal"),
	YELLOW_CARD("yellow_card"),
	RED_CARD("red_card")
}

@Serializable
data class Event(
	val minute: Int,
	val type: EventType,
	val playerId: String,
	val teamId: String?
) {
	init {
		require(minute >= 0 && minute <= 90) { "Minute must be between 0 and 90" }
	}
}
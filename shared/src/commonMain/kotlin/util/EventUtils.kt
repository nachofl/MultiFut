package util

import data.enums.EventType

object EventUtils {
	fun fromName(name: String): EventType? {
		return EventType.values().find { it.name == name }
	}
}
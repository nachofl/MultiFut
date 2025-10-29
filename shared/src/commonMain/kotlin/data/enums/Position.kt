package data.enums

import kotlinx.serialization.Serializable

@Serializable
enum class Position(val displayName: String) {
	Portero("goalkeeper"),
	Defensa("defender"),
	Mediocentro("midfielder"),
	Delantero("forward");

	companion object {
		fun fromDisplayName(displayName: String): Position? {
			return values().find { it.displayName == displayName }
		}
	}
}
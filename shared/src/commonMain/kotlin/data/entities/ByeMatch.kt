package data.entities

import kotlinx.serialization.Serializable

@Serializable
data class ByeMatch(
	override val id: String,
	override val homeTeamId: String,
	override val homeGoals: Int? = null,
	override val actaFilled: Boolean = false,
) : Match() {
	init {
		require(homeGoals == null) { "Home team goals can't be other than null" }
	}
}

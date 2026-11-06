package data.entities

import kotlinx.serialization.Serializable

@Serializable
data class NormalMatch(
	override val id: String,
	override val homeTeamId: String,
	override val homeGoals: Int? = null,
	val awayTeamId: String,
	val awayGoals: Int? = null,
	override val actaFilled: Boolean = false,
) : Match() {
	init {
		require(homeTeamId != awayTeamId) { "Home team and away team can't be the same" }
		require(homeGoals == null || homeGoals >= 0) { "Home team goals can't be negative" }
		require(awayGoals == null || awayGoals >= 0) { "Away team goals can't be negative" }
	}
}

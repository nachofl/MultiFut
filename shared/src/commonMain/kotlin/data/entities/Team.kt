package data.entities

import kotlinx.serialization.Serializable

@Serializable
data class Team(
	val id: String,
	val name: String,
	var leagueId: String? = null,
	var gamesPlayed: Int = 0,
	var gamesWon: Int = 0,
	var gamesDrawn: Int = 0,
	var gamesLost: Int = 0,
	var goalsScored: Int = 0,
	var goalsConceded: Int = 0,
	val ownerId: String
) {
	init {
		require(name.isNotBlank()) { "Team name can't be blank" }
		with(this) {
			require(gamesPlayed >= 0) { "Games played can't be negative" }
			require(gamesWon >= 0) { "Games won can't be negative" }
			require(gamesDrawn >= 0) { "Games drawn can't be negative" }
			require(gamesLost >= 0) { "Games lost can't be negative" }
			require(gamesWon + gamesDrawn + gamesLost <= gamesPlayed) { "Inconsistent game stats" }
			require(goalsScored >= 0) { "Goals scored can't be negative" }
			require(goalsConceded >= 0) { "Goals conceded can't be negative" }
		}
	}
}
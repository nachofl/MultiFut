package data.entities

import kotlinx.serialization.Serializable

@Serializable
data class Team(
	val id: String,
	val name: String,
	val leagueId: String? = null,
	val gamesPlayed: Int = 0,
	val gamesWon: Int = 0,
	val gamesDrawn: Int = 0,
	val gamesLost: Int = 0,
	val goalsScored: Int = 0,
	val goalsConceded: Int = 0,
	val ownerId: String
) {
	init {
		require(id.isNotBlank()) { "Team id can't be blank" }
		require(name.isNotBlank()) { "Team name can't be blank" }
		require(ownerId.isNotBlank()) { "Owner id can't be blank" }
		require(gamesPlayed >= 0) { "Games played can't be negative" }
		require(gamesWon >= 0) { "Games won can't be negative" }
		require(gamesDrawn >= 0) { "Games drawn can't be negative" }
		require(gamesLost >= 0) { "Games lost can't be negative" }
		require(gamesWon + gamesDrawn + gamesLost <= gamesPlayed) { "Inconsistent game stats" }
		require(goalsScored >= 0) { "Goals scored can't be negative" }
		require(goalsConceded >= 0) { "Goals conceded can't be negative" }
	}

	fun changeLeague(newLeagueId: String?): Team = copy(leagueId = newLeagueId)

	fun recordMatch(goalsFor: Int, goalsAgainst: Int): Team {
		require(goalsFor >= 0) { "Goals for can't be negative" }
		require(goalsAgainst >= 0) { "Goals against can't be negative" }

		val newGamesPlayed = gamesPlayed + 1
		val (won, draw, lost) = when {
			goalsFor > goalsAgainst -> Triple(1, 0, 0)
			goalsFor == goalsAgainst -> Triple(0, 1, 0)
			else -> Triple(0, 0, 1)
		}

		return copy(
			gamesPlayed = newGamesPlayed,
			gamesWon = gamesWon + won,
			gamesDrawn = gamesDrawn + draw,
			gamesLost = gamesLost + lost,
			goalsScored = goalsScored + goalsFor,
			goalsConceded = goalsConceded + goalsAgainst
		)
	}

	fun resetSeasonStats(): Team = copy(
		gamesPlayed = 0,
		gamesWon = 0,
		gamesDrawn = 0,
		gamesLost = 0,
		goalsScored = 0,
		goalsConceded = 0
	)
}

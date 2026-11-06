package data.entities

import data.enums.Position
import kotlinx.serialization.Serializable

@Serializable
data class Player(
	val id: String,
	val name: String,
	val position: Position,
	val teamId: Long? = null,
	val gamesPlayed: Int = 0,
	val goals: Int = 0,
	val yellowCards: Int = 0,
	val redCards: Int = 0,
	val gamesPlayedTotal: Int = 0,
	val goalsTotal: Int = 0,
	val yellowCardsTotal: Int = 0,
	val redCardsTotal: Int = 0
) {
	init {
		require(name.isNotBlank()) { "Player name can't be blank" }
		require(gamesPlayed >= 0) { "Games played can't be negative" }
		require(goals >= 0) { "Goals can't be negative" }
		require(yellowCards >= 0) { "Yellow cards can't be negative" }
		require(redCards >= 0) { "Red cards can't be negative" }
		require(gamesPlayedTotal >= 0) { "Games played total can't be negative" }
		require(goalsTotal >= 0) { "Goals total can't be negative" }
		require(yellowCardsTotal >= 0) { "Yellow cards total can't be negative" }
		require(redCardsTotal >= 0) { "Red cards total can't be negative" }
	}

	fun recordMatch(
		goalsScored: Int = 0,
		yellow: Int = 0,
		red: Int = 0,
		countTowardsTotal: Boolean = true
	): Player {
		require(goalsScored >= 0) { "Goals scored can't be negative" }
		require(yellow >= 0) { "Yellow cards can't be negative" }
		require(red >= 0) { "Red cards can't be negative" }

		val newGamesPlayed = gamesPlayed + 1
		val newGoals = goals + goalsScored
		val newYellow = yellowCards + yellow
		val newRed = redCards + red

		return if (countTowardsTotal) {
			copy(
				gamesPlayed = newGamesPlayed,
				goals = newGoals,
				yellowCards = newYellow,
				redCards = newRed,
				gamesPlayedTotal = gamesPlayedTotal + 1,
				goalsTotal = goalsTotal + goalsScored,
				yellowCardsTotal = yellowCardsTotal + yellow,
				redCardsTotal = redCardsTotal + red
			)
		} else {
			copy(
				gamesPlayed = newGamesPlayed,
				goals = newGoals,
				yellowCards = newYellow,
				redCards = newRed
			)
		}
	}

	fun changeTeam(newTeamId: Long?): Player = copy(teamId = newTeamId)

	fun changePosition(newPosition: Position): Player = copy(position = newPosition)

	fun resetSeasonStats(): Player = copy(
		gamesPlayed = 0,
		goals = 0,
		yellowCards = 0,
		redCards = 0
	)
}

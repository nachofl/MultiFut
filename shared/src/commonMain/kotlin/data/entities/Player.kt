package data.entities

import data.enums.Position
import kotlinx.serialization.Serializable

@Serializable
data class Player(
	val id: String,
	val name: String,
	var position: Position,
	var teamId: Long? = null,
	var gamesPlayed: Int = 0,
	var goals: Int = 0,
	var yellowCards: Int = 0,
	var redCards: Int = 0,
	var gamesPlayedTotal: Int = 0,
	var goalsTotal: Int = 0,
	var yellowCardsTotal: Int = 0,
	var redCardsTotal: Int = 0
) {
	init {
		require(name.isNotBlank()) { "Player name can't be blank" }
		require(gamesPlayed >= 0) { "Games played can't be negative" }
		require(goals >= 0) { "Goals can't be negative" }
		require(yellowCards >= 0) { "Yellow cards can't be negative" }
		require(redCards >= 0) { "Red cards can't be negative" }
	}
}
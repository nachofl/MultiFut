package data.entities

import data.enums.Event
import kotlinx.serialization.Serializable

@Serializable
data class Acta(
	val id: String = "",
	val matchId: String = "",
	val homeGoals: Int = 0,
	val homeTeamStartingPlayers: List<String> = emptyList(),
	val homeTeamSubstitutes: List<String> = emptyList(),
	val awayGoals: Int = 0,
	val awayTeamStartingPlayers: List<String> = emptyList(),
	val awayTeamSubstitutes: List<String> = emptyList(),
	val events: List<Event> = emptyList()
) {
	init {
		require(homeGoals >= 0) { "Home Team Goals can't be negative" }
		require(awayGoals >= 0) { "Away Team Goals can't be negative" }
		require(homeTeamStartingPlayers.isEmpty() || homeTeamStartingPlayers.size == 7) {
			"Home Team Starting must have 7 players"
		}
		require(homeTeamSubstitutes.size <= 5) {
			"Home Team Substitutes must have 5 or fewer players"
		}
		require(awayTeamStartingPlayers.isEmpty() || awayTeamStartingPlayers.size == 7) {
			"Away Team Starting must have 7 players"
		}
		require(awayTeamSubstitutes.size <= 5) {
			"Away Team Substitutes must have 5 or fewer players"
		}
		require(homeTeamStartingPlayers.all { it.isNotBlank() }) { "Home starting player ids can't be blank" }
		require(homeTeamSubstitutes.all { it.isNotBlank() }) { "Home substitute ids can't be blank" }
		require(awayTeamStartingPlayers.all { it.isNotBlank() }) { "Away starting player ids can't be blank" }
		require(awayTeamSubstitutes.all { it.isNotBlank() }) { "Away substitute ids can't be blank" }
	}

	fun changeId(newId: String): Acta {
		require(newId.isNotBlank()) { "Id can't be blank" }
		return copy(id = newId)
	}

	fun changeMatchId(newMatchId: String): Acta {
		require(newMatchId.isNotBlank()) { "Match id can't be blank" }
		return copy(matchId = newMatchId)
	}

	fun setHomeGoals(goals: Int): Acta {
		require(goals >= 0) { "Home Team Goals can't be negative" }
		return copy(homeGoals = goals)
	}

	fun setAwayGoals(goals: Int): Acta {
		require(goals >= 0) { "Away Team Goals can't be negative" }
		return copy(awayGoals = goals)
	}

	fun setHomeStartingPlayers(players: List<String>): Acta {
		require(players.isEmpty() || players.size == 7) { "Home Team Starting must have 7 players" }
		require(players.all { it.isNotBlank() }) { "Home starting player ids can't be blank" }
		return copy(homeTeamStartingPlayers = players)
	}

	fun setAwayStartingPlayers(players: List<String>): Acta {
		require(players.isEmpty() || players.size == 7) { "Away Team Starting must have 7 players" }
		require(players.all { it.isNotBlank() }) { "Away starting player ids can't be blank" }
		return copy(awayTeamStartingPlayers = players)
	}

	fun setHomeSubstitutes(subs: List<String>): Acta {
		require(subs.size <= 5) { "Home Team Substitutes must have 5 or fewer players" }
		require(subs.all { it.isNotBlank() }) { "Home substitute ids can't be blank" }
		return copy(homeTeamSubstitutes = subs)
	}

	fun setAwaySubstitutes(subs: List<String>): Acta {
		require(subs.size <= 5) { "Away Team Substitutes must have 5 or fewer players" }
		require(subs.all { it.isNotBlank() }) { "Away substitute ids can't be blank" }
		return copy(awayTeamSubstitutes = subs)
	}

	fun addEvents(updatedEvents: List<Event>): Acta = copy(events = updatedEvents)

	fun clearEvents(): Acta = copy(events = emptyList())
}

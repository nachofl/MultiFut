package data.entities

import kotlinx.serialization.Serializable
import data.enums.Event

@Serializable
data class Acta(
    val id: String,
    val matchId: Long,
    var homeGoals: Int?,
    var homeTeamStartingPlayers: List<Long>,
    var homeTeamSubstitutes: List<Long>,
    var awayGoals: Int?,
    var awayTeamStartingPlayers: List<Long>,
    var awayTeamSubstitutes: List<Long>,
    var events: List<Event>
) {
    init {
        require(homeGoals!! >= 0) { "Home Team Goals can't be negative" }
        require(awayGoals!! >= 0) { "Away Team Goals can't be negative" }
        require(homeTeamStartingPlayers.size == 7 || homeTeamStartingPlayers.isEmpty()) { "Home Team Starting must have 7 players"}
        require(homeTeamSubstitutes.size <= 5) { "Home Team Substitutes must have 5 or less players"}
        require(awayTeamStartingPlayers.size == 7 || awayTeamStartingPlayers.isEmpty()) { "Away Team Starting must have 7 players"}
        require(awayTeamSubstitutes.size <=5) { "Away Team Substitutes must have 5 or less players"}
    }
}
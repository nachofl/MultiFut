package data.entities

import kotlinx.serialization.Serializable

@Serializable
data class Match(
    val id: String,
    val homeTeamId: Long,
    var homeGoals: Int? = null,
    val awayTeamId: Long?,
    var awayGoals: Int? = null,
    var actaFilled: Boolean = false,
){
    init{
        require(homeTeamId != awayTeamId) { "Home team and away team can't be the same" }
        if (homeGoals != null) {
            require(homeGoals!! >= 0) { "Home team goals can't be negative" }
        }
        if (awayGoals != null) {
            require(awayGoals!! >= 0) { "Away team goals can't be negative" }
        }
    }
}

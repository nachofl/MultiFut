package data.entities

import data.entities.Match
import kotlinx.serialization.Serializable

@Serializable
data class League(
    val id: String,
    val name: String,
    var numTeams: Int = 0,
    val calendar: List<List<Match>>,
    val ownerId: Long,
    var ended: Boolean = false
){
    init {
        require(name.isNotBlank()) { "League name can't be blank" }
        require(numTeams >= 0) { "Number of teams can't be negative" }
    }
}

package data.entities

import kotlinx.serialization.Serializable

@Serializable
data class League(
	val id: String,
	val name: String,
	val numTeams: Int = 0,
	val calendar: List<List<Match>> = emptyList(),
	val ownerId: String,
	val ended: Boolean = false
) {
	init {
		require(id.isNotBlank()) { "League id can't be blank" }
		require(name.isNotBlank()) { "League name can't be blank" }
		require(numTeams >= 0) { "Number of teams can't be negative" }
		require(ownerId.isNotBlank()) { "Owner id can't be blank" }
		require(calendar.all { it.isNotEmpty() }) { "Rounds in calendar can't be empty" }
		if (numTeams > 0) {
			require(calendar.all { it.size == numTeams }) { "Every round must contain exactly numTeams matches/entries" }
		}
	}

	fun changeName(newName: String): League {
		require(newName.isNotBlank()) { "League name can't be blank" }
		return copy(name = newName)
	}

	fun changeOwner(newOwnerId: String): League {
		require(newOwnerId.isNotBlank()) { "Owner id can't be blank" }
		return copy(ownerId = newOwnerId)
	}

	fun changeNumTeams(newNumTeams: Int): League {
		require(newNumTeams >= 0) { "Number of teams can't be negative" }
		return copy(numTeams = newNumTeams)
	}

	fun setCalendar(newCalendar: List<List<Match>>): League {
		require(newCalendar.all { it.isNotEmpty() }) { "Rounds in calendar can't be empty" }
		if (numTeams > 0) require(newCalendar.all { it.size == numTeams }) { "Every round must contain exactly numTeams entries" }
		return copy(calendar = newCalendar)
	}

	fun toggleEnded(): League = copy(ended = !ended)

	fun setEnded(value: Boolean): League = copy(ended = value)

	fun resetCalendar(): League = copy(calendar = emptyList(), ended = false)
}
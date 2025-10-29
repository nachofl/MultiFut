package data.entities

import kotlinx.serialization.Serializable

@Serializable
data class User(
	val id: String,
	var login: String,
	var firstName: String,
	var lastName: String,
	var playerId: String? = null,
	var favoriteLeagues: List<String>,
	var favoriteTeams: List<String>,
	var favoritePlayers: List<String>
) {
	init {
		require(login.isNotBlank()) { "Login can't be blank" }
		require(firstName.isNotBlank()) { "First name can't be blank" }
		require(lastName.isNotBlank()) { "Last name can't be blank" }
	}
}
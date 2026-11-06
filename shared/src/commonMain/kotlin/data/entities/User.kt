package data.entities

import kotlinx.serialization.Serializable

@Serializable
data class User(
	val id: String,
	val login: String,
	val firstName: String,
	val lastName: String,
	val playerId: String? = null,
	val favoriteLeagues: List<String> = emptyList(),
	val favoriteTeams: List<String> = emptyList(),
	val favoritePlayers: List<String> = emptyList()
) {
	init {
		require(id.isNotBlank()) { "User id can't be blank" }
		require(login.isNotBlank()) { "Login can't be blank" }
		require(firstName.isNotBlank()) { "First name can't be blank" }
		require(lastName.isNotBlank()) { "Last name can't be blank" }
		require(favoriteLeagues.all { it.isNotBlank() }) { "Favorite leagues can't contain blank ids" }
		require(favoriteTeams.all { it.isNotBlank() }) { "Favorite teams can't contain blank ids" }
		require(favoritePlayers.all { it.isNotBlank() }) { "Favorite players can't contain blank ids" }
	}

	fun changeLogin(newLogin: String): User {
		require(newLogin.isNotBlank()) { "Login can't be blank" }
		return copy(login = newLogin)
	}

	fun changeName(newFirstName: String, newLastName: String): User {
		require(newFirstName.isNotBlank()) { "First name can't be blank" }
		require(newLastName.isNotBlank()) { "Last name can't be blank" }
		return copy(firstName = newFirstName, lastName = newLastName)
	}

	fun setPlayerId(newPlayerId: String?): User = copy(playerId = newPlayerId)

	fun addFavoriteLeague(leagueId: String): User {
		require(leagueId.isNotBlank()) { "League id can't be blank" }
		return if (favoriteLeagues.contains(leagueId)) this else copy(favoriteLeagues = favoriteLeagues + leagueId)
	}

	fun removeFavoriteLeague(leagueId: String): User =
		copy(favoriteLeagues = favoriteLeagues - leagueId)

	fun addFavoriteTeam(teamId: String): User {
		require(teamId.isNotBlank()) { "Team id can't be blank" }
		return if (favoriteTeams.contains(teamId)) this else copy(favoriteTeams = favoriteTeams + teamId)
	}

	fun removeFavoriteTeam(teamId: String): User =
		copy(favoriteTeams = favoriteTeams - teamId)

	fun addFavoritePlayer(playerId: String): User {
		require(playerId.isNotBlank()) { "Player id can't be blank" }
		return if (favoritePlayers.contains(playerId)) this else copy(favoritePlayers = favoritePlayers + playerId)
	}

	fun removeFavoritePlayer(playerId: String): User =
		copy(favoritePlayers = favoritePlayers - playerId)

	fun clearFavorites(): User = copy(
		favoriteLeagues = emptyList(),
		favoriteTeams = emptyList(),
		favoritePlayers = emptyList()
	)
}

package repository

import data.entities.Team
import kotlinx.coroutines.flow.Flow

interface TeamRepository {
	fun observeTeams(query: String, onlyNoLeague: Boolean): Flow<List<Team>>

	fun observeTeamsByLeague(leagueId: String): Flow<List<Team>>
	suspend fun getTeamsByLeague(leagueId: String): List<Team>

	fun getAllTeamsAddTeam(): Flow<List<Team>>

	suspend fun createTeam(name: String, ownerId: String): String
	suspend fun updateTeam(team: Team)
	suspend fun removeTeam(teamId: String)

	suspend fun getTeamById(teamId: String?): Team?

	suspend fun addPlayerToTeam(playerId: String, teamId: String)
}

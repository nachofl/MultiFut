package viewmodel

import data.entities.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import repository.TeamRepository

class TeamViewModel(
	private val teamRepository: TeamRepository
) : BaseViewModel() {

	private val searchQuery = MutableStateFlow("")
	private val filterNoLeague = MutableStateFlow(false)

	@OptIn(ExperimentalCoroutinesApi::class)
	val teams: Flow<List<Team>> =
		combine(searchQuery, filterNoLeague) { query, noLeague -> query to noLeague }
			.flatMapLatest { (query, noLeague) ->
				teamRepository.observeTeams(query, noLeague)
			}

	fun updateSearchQuery(query: String, noLeagueFilter: Boolean = false) {
		searchQuery.value = query
		filterNoLeague.value = noLeagueFilter
	}

	suspend fun createTeam(name: String, ownerId: String): String =
		teamRepository.createTeam(name, ownerId)

	fun getTeamsByLeague(leagueId: String): Flow<List<Team>> =
		teamRepository.observeTeamsByLeague(leagueId)

	suspend fun getTeamsByLeagueList(leagueId: String): List<Team> =
		withContext(Dispatchers.IO) { teamRepository.getTeamsByLeague(leagueId) }

	fun getAllTeamsAddTeam(): Flow<List<Team>> =
		teamRepository.getAllTeamsAddTeam()

	suspend fun getTeamById(teamId: String?): Team? =
		withContext(Dispatchers.IO) { teamRepository.getTeamById(teamId) }

	fun addPlayerToTeam(team: Team, playerId: String) {
		coroutineScope.launch { teamRepository.addPlayerToTeam(playerId, team.id) }
	}

	suspend fun updateTeam(team: Team) =
		teamRepository.updateTeam(team)

	fun removeTeam(team: Team) {
		coroutineScope.launch { teamRepository.removeTeam(team.id) }
	}
}
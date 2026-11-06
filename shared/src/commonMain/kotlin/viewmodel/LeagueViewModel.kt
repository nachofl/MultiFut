package viewmodel

import data.entities.Acta
import data.entities.League
import data.entities.Match
import data.entities.Team
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import repository.LeagueRepository

class LeagueViewModel(
	private val leagueRepository: LeagueRepository
) : BaseViewModel() {

	private val searchQuery = MutableStateFlow("")

	@OptIn(ExperimentalCoroutinesApi::class)
	val leagues: Flow<List<League>> =
		searchQuery.flatMapLatest { query -> leagueRepository.observeLeagues(query) }

	fun updateSearchQuery(query: String) {
		searchQuery.value = query
	}

	suspend fun createLeague(name: String, ownerId: String): String =
		leagueRepository.createLeague(name, ownerId)

	suspend fun updateLeague(league: League) =
		leagueRepository.updateLeague(league)

	suspend fun removeLeague(league: League): Boolean {
		leagueRepository.removeLeague(league.id)
		return true
	}

	suspend fun getAllLeaguesList(): List<League> =
		leagueRepository.getAllLeaguesList()

	suspend fun getLeagueById(leagueId: String): League =
		leagueRepository.getLeagueById(leagueId)

	suspend fun addTeamToLeague(league: League, team: Team) =
		leagueRepository.addTeamToLeague(league.id, team.id)

	suspend fun addCalendarToLeague(league: League, calendar: List<List<Match>>) =
		leagueRepository.addCalendarToLeague(league.id, calendar)

	fun insertMatches(jornada: List<Match>) {
		coroutineScope.launch { leagueRepository.insertMatches(jornada) }
	}

	suspend fun insertMatch(match: Match): String =
		leagueRepository.insertMatch(match)

	suspend fun updateMatch(match: Match) =
		leagueRepository.updateMatch(match)

	suspend fun removeMatch(match: Match): Boolean {
		leagueRepository.removeMatch(match.id)
		return true
	}

	suspend fun getMatchById(matchId: String): Match =
		leagueRepository.getMatchById(matchId)

	suspend fun getAllMatches(): List<Match> =
		leagueRepository.getAllMatches()

	suspend fun getMatchIdsFromLeague(leagueId: String): List<String> =
		leagueRepository.getLeagueById(leagueId).calendar.flatten().map { it.id }

	suspend fun insertActa(acta: Acta): String =
		leagueRepository.insertActa(acta)

	suspend fun updateActa(acta: Acta): String? =
		leagueRepository.updateActa(acta)

	suspend fun removeActa(acta: Acta): Boolean {
		leagueRepository.removeActa(acta.id)
		return true
	}

	suspend fun getActaByMatchId(matchId: String?): Acta? =
		leagueRepository.getActaByMatchId(matchId)

	suspend fun getActaById(actaId: String?): Acta? =
		leagueRepository.getActaById(actaId)
}

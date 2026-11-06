package repository

import data.entities.Acta
import data.entities.League
import data.entities.Match
import kotlinx.coroutines.flow.Flow

interface LeagueRepository {
	fun observeLeagues(query: String): Flow<List<League>>

	suspend fun createLeague(name: String, ownerId: String): String
	suspend fun updateLeague(league: League)
	suspend fun removeLeague(leagueId: String)

	suspend fun getAllLeaguesList(): List<League>
	suspend fun getLeagueById(leagueId: String): League

	suspend fun addTeamToLeague(leagueId: String, teamId: String)
	suspend fun addCalendarToLeague(leagueId: String, calendar: List<List<Match>>)

	suspend fun insertMatches(matches: List<Match>)
	suspend fun insertMatch(match: Match): String
	suspend fun updateMatch(match: Match)
	suspend fun removeMatch(matchId: String)
	suspend fun getMatchById(matchId: String): Match
	suspend fun getAllMatches(): List<Match>

	suspend fun insertActa(acta: Acta): String
	suspend fun updateActa(acta: Acta): String?
	suspend fun removeActa(actaId: String)
	suspend fun getActaByMatchId(matchId: String?): Acta?
	suspend fun getActaById(actaId: String?): Acta?
}

package viewmodel

import data.entities.Player
import data.enums.Position
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.withContext
import repository.PlayerRepository

class PlayerViewModel(
	private val playerRepository: PlayerRepository
) : BaseViewModel() {

	private val searchQuery = MutableStateFlow("")
	private val filterNoTeam = MutableStateFlow(false)

	@OptIn(ExperimentalCoroutinesApi::class)
	val players: Flow<List<Player>> =
		combine(searchQuery, filterNoTeam) { query, noTeam -> query to noTeam }
			.flatMapLatest { (query, noTeam) ->
				playerRepository.observePlayers(query, noTeam)
			}

	fun updateSearchQuery(query: String, noTeamFilter: Boolean = false) {
		searchQuery.value = query
		filterNoTeam.value = noTeamFilter
	}

	suspend fun createPlayer(name: String, position: Position): String =
		playerRepository.createPlayer(name, position)

	suspend fun updatePlayer(player: Player) =
		playerRepository.updatePlayer(player)

	fun getPlayersByTeam(teamId: String): Flow<List<Player>> =
		playerRepository.observePlayersByTeam(teamId)

	suspend fun getPlayersByTeamList(teamId: String): List<Player> =
		withContext(Dispatchers.IO) { playerRepository.getPlayersByTeam(teamId) }

	suspend fun getPlayerById(playerId: String?): Player? =
		withContext(Dispatchers.IO) { playerRepository.getPlayerById(playerId) }
}

package repository

import data.entities.Player
import data.enums.Position
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
	fun observePlayers(query: String, filterNoTeam: Boolean): Flow<List<Player>>

	fun observePlayersByTeam(teamId: String): Flow<List<Player>>
	suspend fun getPlayersByTeam(teamId: String): List<Player>

	suspend fun createPlayer(name: String, position: Position): String
	suspend fun updatePlayer(player: Player)
	suspend fun getPlayerById(playerId: String?): Player?
}

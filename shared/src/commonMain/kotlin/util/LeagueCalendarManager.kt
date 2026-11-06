package util

import data.entities.Match
import data.entities.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class LeagueCalendarManager {

	/**
	 * Genera jornadas (una vuelta) con el algoritmo round-robin.
	 * Devuelve listas de pares (homeId, awayId) donde `null` representa un BYE.
	 */
	fun generateRoundRobinTeamIds(teams: List<Team>): List<List<Pair<String?, String?>>> {
		if (teams.size < 2) throw InvalidNumTeamsCalendarException(teams.size)

		val working: MutableList<String?> = teams.map { it.id }.toMutableList()
		if (working.size % 2 != 0) working.add(null) // bye como `null`

		val n = working.size
		val rounds = mutableListOf<List<Pair<String?, String?>>>()
		val fixed = working[0]
		val rotables = working.subList(1, n).toMutableList()

		val numRounds = n - 1
		for (r in 0 until numRounds) {
			val matches = mutableListOf<Pair<String?, String?>>()

			val a = fixed
			val b = rotables[rotables.size - 1]
			matches.add(Pair(a, b))

			for (i in 0 until rotables.size / 2) {
				val t1 = rotables[i]
				val t2 = rotables[rotables.size - 1 - i]
				matches.add(Pair(t1, t2))
			}

			rounds.add(matches)

			val last = rotables.removeAt(rotables.size - 1)
			rotables.add(0, last)
		}

		return rounds
	}

	/**
	 * Genera doble vuelta (ida + vuelta invirtiendo local/visitante).
	 */
	fun generateDoubleRoundRobinTeamIds(teams: List<Team>): List<List<Pair<String?, String?>>> {
		val firstHalf = generateRoundRobinTeamIds(teams)
		val secondHalf = firstHalf.map { round ->
			round.map { (home, away) -> Pair(away, home) }
		}
		return firstHalf + secondHalf
	}

	/**
	 * Obtiene equipos desde el Flow, genera calendario doble y persiste usando las funciones proporcionadas:
	 * - insertMatch: recibe (homeId, awayId) y devuelve el id persistido (String)
	 * - getMatchById: recibe id y devuelve el objeto Match
	 *
	 * Esto evita acoplarse directamente a la firma de `LeagueViewModel`.
	 */
	suspend fun generateAndSave(
		teamsFlow: Flow<List<Team>>,
		insertMatch: suspend (homeId: String?, awayId: String?) -> String,
		getMatchById: suspend (String) -> Match
	): List<List<Match>> {
		val teams = try {
			teamsFlow.first()
		} catch (e: Exception) {
			throw InvalidTeamsFlowException()
		}

		val calendarioIds = try {
			generateDoubleRoundRobinTeamIds(teams)
		} catch (e: InvalidNumTeamsCalendarException) {
			throw e
		} catch (e: Exception) {
			throw InvalidCalendarMatchException()
		}

		val persisted = mutableListOf<List<Match>>()
		for (round in calendarioIds) {
			val persistedRound = mutableListOf<Match>()
			for ((homeId, awayId) in round) {
				try {
					val id = insertMatch(homeId, awayId)
					val persistedMatch = getMatchById(id)
					persistedRound.add(persistedMatch)
				} catch (e: Exception) {
					throw InvalidCalendarMatchException()
				}
			}
			persisted.add(persistedRound)
		}

		return persisted
	}
}

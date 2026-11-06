package data.entities

import kotlinx.serialization.Serializable

@Serializable
sealed class Match {
	abstract val id: String
	abstract val homeTeamId: String
	abstract val homeGoals: Int?
	abstract val actaFilled: Boolean

	fun isBye(): Boolean = this is ByeMatch

	fun isPlayed(): Boolean = when (this) {
		is NormalMatch -> homeGoals != null && awayGoals != null
		is ByeMatch -> false
	}

	fun isDraw(): Boolean = when (this) {
		is NormalMatch -> isPlayed() && homeGoals == awayGoals
		is ByeMatch -> false
	}

	fun winnerTeamId(): String? {
		return when (this) {
			is NormalMatch -> {
				if (!isPlayed()) return null
				when {
					homeGoals!! > awayGoals!! -> homeTeamId
					homeGoals!! < awayGoals -> awayTeamId
					else -> null
				}
			}

			is ByeMatch -> null
		}
	}

	fun scoreString(): String = when (this) {
		is NormalMatch -> "${homeGoals?.toString() ?: "-"} - ${awayGoals?.toString() ?: "-"}"
		is ByeMatch -> "${homeGoals?.toString() ?: "-"} - -"
	}
}


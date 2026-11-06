package data

import kotlin.time.Clock

data class UserProfile(
	val uid: String,
	val email: String,
	val displayName: String? = null,
	val photoUrl: String? = null,
	val languageCode: String = "en",
	val createdAt: Long = Clock.System.now().toEpochMilliseconds(),
	val updatedAt: Long = Clock.System.now().toEpochMilliseconds()
)

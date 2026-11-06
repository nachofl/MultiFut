package repository

interface SessionRepository {
	fun saveUserSession(userId: String)
	fun saveGuestSession()
	fun clearSession()
	fun getUserId(): String?
}

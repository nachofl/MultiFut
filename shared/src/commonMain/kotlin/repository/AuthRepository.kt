package repository

interface AuthRepository {
	suspend fun signIn(email: String, password: String): Result<String>
	suspend fun signUp(email: String, password: String): Result<String>
	fun signOut()
	fun currentUserId(): String?
}
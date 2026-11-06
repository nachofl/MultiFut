package auth

import repository.AuthRepository

class IosAuthRepository : AuthRepository {
	override suspend fun signIn(email: String, password: String): Result<String> =
		Result.failure(UnsupportedOperationException("signIn no implementado en iOS"))

	override suspend fun signUp(email: String, password: String): Result<String> =
		Result.failure(UnsupportedOperationException("signUp no implementado en iOS"))

	override fun signOut() {}

	override fun currentUserId(): String? = null
}
package repository

import data.UserProfile

class IosUserRepository : UserRepository {
	override suspend fun getProfile(uid: String): Result<UserProfile?> =
		Result.failure(UnsupportedOperationException("getProfile no implementado en iOS"))

	override suspend fun createDefaultProfile(uid: String, email: String): Result<UserProfile> =
		Result.failure(UnsupportedOperationException("createDefaultProfile no implementado en iOS"))

	override suspend fun updateProfile(profile: UserProfile): Result<Unit> =
		Result.failure(UnsupportedOperationException("updateProfile no implementado en iOS"))
}
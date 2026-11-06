package repository

import data.UserProfile

interface UserRepository {
	suspend fun getProfile(uid: String): Result<UserProfile?>
	suspend fun createDefaultProfile(uid: String, email: String): Result<UserProfile>
	suspend fun updateProfile(profile: UserProfile): Result<Unit>
}

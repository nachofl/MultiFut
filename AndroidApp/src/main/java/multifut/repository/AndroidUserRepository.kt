package multifut.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import data.UserProfile
import kotlinx.coroutines.tasks.await
import repository.UserRepository

class AndroidUserRepository(
	private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) : UserRepository {

	private fun users() = db.collection("users")

	override suspend fun getProfile(uid: String): Result<UserProfile?> = runCatching {
		val snap = users().document(uid).get().await()
		if (!snap.exists()) return@runCatching null
		val data = snap.data ?: return@runCatching null
		UserProfile(
			uid = uid,
			email = data["email"] as? String ?: "",
			displayName = data["displayName"] as? String,
			photoUrl = data["photoUrl"] as? String,
			languageCode = data["languageCode"] as? String ?: "en",
			createdAt = (data["createdAt"] as? Number)?.toLong() ?: 0L,
			updatedAt = (data["updatedAt"] as? Number)?.toLong() ?: 0L
		)
	}

	override suspend fun createDefaultProfile(uid: String, email: String): Result<UserProfile> =
		runCatching {
			val now = System.currentTimeMillis()
			val profile = UserProfile(
				uid = uid,
				email = email,
				displayName = null,
				photoUrl = null,
				languageCode = "en",
				createdAt = now,
				updatedAt = now
			)
			users().document(uid)
				.set(profileToMap(profile), SetOptions.merge())
				.await()
			profile
		}

	override suspend fun updateProfile(profile: UserProfile): Result<Unit> = runCatching {
		val updated = profile.copy(updatedAt = System.currentTimeMillis())
		users().document(profile.uid)
			.set(profileToMap(updated), SetOptions.merge())
			.await()
	}

	private fun profileToMap(p: UserProfile) = mapOf(
		"email" to p.email,
		"displayName" to p.displayName,
		"photoUrl" to p.photoUrl,
		"languageCode" to p.languageCode,
		"createdAt" to p.createdAt,
		"updatedAt" to p.updatedAt
	)
}

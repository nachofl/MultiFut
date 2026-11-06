package auth

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import data.UserProfile
import kotlinx.coroutines.suspendCancellableCoroutine
import repository.UserRepository
import kotlin.coroutines.resume

class AndroidUserRepository : UserRepository {
	private val db = FirebaseFirestore.getInstance()
	private val users = db.collection("users")

	override suspend fun getProfile(uid: String): Result<UserProfile?> =
		suspendCancellableCoroutine { cont ->
			users.document(uid).get()
				.addOnSuccessListener { snap ->
					if (snap.exists()) {
						val email = snap.getString("email") ?: ""
						val displayName = snap.getString("displayName")
						val photoUrl = snap.getString("photoUrl")
						val languageCode = snap.getString("languageCode") ?: "en"
						val createdAt = snap.getLong("createdAt") ?: System.currentTimeMillis()
						val updatedAt = snap.getLong("updatedAt") ?: createdAt
						val profile = UserProfile(
							uid,
							email,
							displayName,
							photoUrl,
							languageCode,
							createdAt,
							updatedAt
						)
						cont.resume(Result.success(profile))
					} else {
						cont.resume(Result.success(null))
					}
				}
				.addOnFailureListener { e -> cont.resume(Result.failure(e)) }
		}

	override suspend fun createDefaultProfile(uid: String, email: String): Result<UserProfile> =
		suspendCancellableCoroutine { cont ->
			val now = System.currentTimeMillis()
			val data = mapOf<String, Any?>(
				"uid" to uid,
				"email" to email,
				"displayName" to null,
				"photoUrl" to null,
				"languageCode" to "en",
				"createdAt" to now,
				"updatedAt" to now
			)
			users.document(uid).set(data, SetOptions.merge())
				.addOnSuccessListener {
					val profile = UserProfile(uid, email, null, null, "en", now, now)
					cont.resume(Result.success(profile))
				}
				.addOnFailureListener { e -> cont.resume(Result.failure(e)) }
		}

	override suspend fun updateProfile(profile: UserProfile): Result<Unit> =
		suspendCancellableCoroutine { cont ->
			val updates = mapOf<String, Any?>(
				"displayName" to profile.displayName,
				"photoUrl" to profile.photoUrl,
				"languageCode" to profile.languageCode,
				"updatedAt" to System.currentTimeMillis()
			)
			users.document(profile.uid).set(updates, SetOptions.merge())
				.addOnSuccessListener { cont.resume(Result.success(Unit)) }
				.addOnFailureListener { e -> cont.resume(Result.failure(e)) }
		}
}

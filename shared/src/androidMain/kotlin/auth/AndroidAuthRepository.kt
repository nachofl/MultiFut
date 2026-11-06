package auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import repository.AuthRepository

class AndroidAuthRepository(
	private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) : AuthRepository {

	override suspend fun signIn(email: String, password: String): Result<String> = runCatching {
		withContext(Dispatchers.IO) {
			val user = auth.signInWithEmailAndPassword(email, password).await().user
				?: throw IllegalStateException("Usuario no disponible")
			user.uid
		}
	}

	override suspend fun signUp(email: String, password: String): Result<String> = runCatching {
		withContext(Dispatchers.IO) {
			val user = auth.createUserWithEmailAndPassword(email, password).await().user
				?: throw IllegalStateException("Usuario no disponible")
			user.uid
		}
	}

	override fun signOut() {
		auth.signOut()
	}

	override fun currentUserId(): String? = auth.currentUser?.uid
}

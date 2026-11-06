package viewmodel

import data.session.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import repository.AuthRepository
import repository.UserRepository

class AuthViewModel(
	private val repo: AuthRepository,
	private val userRepo: UserRepository,
	private val session: SessionManager
) {
	private val _state = MutableStateFlow<AuthState>(AuthState.LoggedOut)
	val state: StateFlow<AuthState> = _state

	suspend fun login(email: String, password: String): Result<Unit> {
		val r = repo.signIn(email, password)
		r.onSuccess { uid ->
			session.saveUserSession(uid)
			_state.value = AuthState.LoggedIn(uid)
		}.onFailure {
			_state.value = AuthState.Error(it.message ?: "Error de autenticación")
		}
		return r.map { }
	}

	suspend fun register(email: String, password: String): Result<Unit> {
		val result = runCatching {
			val uid = repo.signUp(email, password).getOrThrow()
			session.saveUserSession(uid)
			userRepo.createDefaultProfile(uid, email).getOrThrow()
			uid
		}
		result.onSuccess { uid ->
			_state.value = AuthState.LoggedIn(uid)
		}.onFailure {
			repo.signOut()
			session.clearSession()
			_state.value = AuthState.Error(it.message ?: "No se pudo registrar")
		}
		return result.map { }
	}

	fun logout() {
		repo.signOut()
		session.clearSession()
		_state.value = AuthState.LoggedOut
	}
}

sealed class AuthState {
	data object LoggedOut : AuthState()
	data class LoggedIn(val uid: String) : AuthState()
	data class Error(val message: String) : AuthState()
}

package viewmodel

import data.session.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import data.UserProfile
import repository.UserRepository

class UserViewModel(
	private val repo: UserRepository,
	private val session: SessionManager
) {
	private val _profile = MutableStateFlow<UserProfile?>(null)
	val profile: StateFlow<UserProfile?> = _profile

	suspend fun loadCurrentUser() {
		val uid = session.getUserId() ?: run {
			_profile.value = null
			return
		}
		repo.getProfile(uid)
			.onSuccess { _profile.value = it }
			.onFailure { _profile.value = null }
	}

	suspend fun updateProfile(updated: UserProfile): Result<Unit> {
		val r = repo.updateProfile(updated)
		r.onSuccess { _profile.value = updated }
		return r
	}
}

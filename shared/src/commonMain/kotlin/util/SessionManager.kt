package data.session

import repository.SessionRepository

class SessionManager : SessionRepository {
	private var currentUserId: String? = null
	private var guest: Boolean = false
	private var languageCode: String = DEFAULT_LANGUAGE

	companion object {
		private const val DEFAULT_LANGUAGE: String = "en"
	}

	override fun saveUserSession(userId: String) {
		currentUserId = userId
		guest = false
	}

	override fun saveGuestSession() {
		currentUserId = null
		guest = true
	}

	override fun clearSession() {
		currentUserId = null
		guest = false
	}

	override fun getUserId(): String? = currentUserId

	fun isGuest(): Boolean = guest

	fun saveLanguage(code: String) {
		if (code.isNotBlank()) {
			languageCode = code.lowercase()
		}
	}

	fun getLanguageCode(): String = languageCode

	fun setInitialLanguage() {}
}

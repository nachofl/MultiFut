package multifut

import android.app.Application
import com.multifut.shared.db.DatabaseContext
import data.session.SessionManager

class FutAppApplication : Application() {

	lateinit var sessionManager: SessionManager
		private set

	override fun onCreate() {
		super.onCreate()
		DatabaseContext.applicationContext = this
		sessionManager = SessionManager()
		sessionManager.setInitialLanguage()
	}
}

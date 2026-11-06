package com.multifut.shared.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

/**
 * Holds the application context needed by the Android SQLite driver.
 * It must be initialized from the Android Application (e.g. in `onCreate`).
 */
object DatabaseContext {
	lateinit var applicationContext: Context
}

actual fun createDatabaseDriver(): SqlDriver =
	AndroidSqliteDriver(AppDatabase.Schema, DatabaseContext.applicationContext, "app.db")

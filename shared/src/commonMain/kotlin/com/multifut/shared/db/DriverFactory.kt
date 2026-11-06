package com.multifut.shared.db

import app.cash.sqldelight.db.SqlDriver

/**
 * Creates the platform-specific SQLDelight driver.
 */
expect fun createDatabaseDriver(): SqlDriver

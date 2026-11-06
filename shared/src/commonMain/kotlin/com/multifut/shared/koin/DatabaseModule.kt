package com.multifut.shared.koin

import com.multifut.shared.db.AppDatabase
import com.multifut.shared.db.createDatabaseDriver
import org.koin.dsl.module

// Provides the SQLDelight database instance
val databaseModule = module {
	single { AppDatabase(createDatabaseDriver()) }
}

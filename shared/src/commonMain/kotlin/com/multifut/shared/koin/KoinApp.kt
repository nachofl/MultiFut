package com.multifut.shared.koin

import org.koin.core.context.startKoin
import org.koin.core.module.Module

/**
 * Starts Koin with the provided modules.
 * Call this in the entry point of your application (e.g., Android Application class).
 */
fun startKoinApp(modules: List<Module> = listOf(databaseModule)) {
    startKoin {
        modules(modules)
    }
}

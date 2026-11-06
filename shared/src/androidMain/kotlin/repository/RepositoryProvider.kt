package repository

import auth.AndroidAuthRepository
import auth.AndroidUserRepository

actual fun provideAuthRepository(): AuthRepository = AndroidAuthRepository()
actual fun provideUserRepository(): UserRepository = AndroidUserRepository()

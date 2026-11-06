package repository

import auth.IosAuthRepository

actual fun provideAuthRepository(): repository.AuthRepository = IosAuthRepository()
actual fun provideUserRepository(): UserRepository = IosUserRepository()
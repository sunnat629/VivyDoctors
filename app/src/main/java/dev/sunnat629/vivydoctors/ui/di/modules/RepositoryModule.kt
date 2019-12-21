package dev.sunnat629.vivydoctors.ui.di.modules

import dagger.Binds
import dagger.Module
import dev.sunnat629.vivydoctors.data.doctors.repository.GitHubRepository

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(authDataRepository: GitHubRepository): GitHubRepository
}
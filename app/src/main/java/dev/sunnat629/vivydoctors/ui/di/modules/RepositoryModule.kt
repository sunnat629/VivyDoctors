package dev.sunnat629.vivydoctors.ui.di.modules

import dagger.Binds
import dagger.Module
import dev.sunnat629.vivydoctors.data.doctors.VivyDoctorsDataRepository
import dev.sunnat629.vivydoctors.domain.doctors.VivyDoctorsRepository

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindVivyDoctorsDataRepository(doctorsDataRepository: VivyDoctorsDataRepository): VivyDoctorsRepository
}
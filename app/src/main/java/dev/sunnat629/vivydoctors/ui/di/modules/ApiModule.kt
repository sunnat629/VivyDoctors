package dev.sunnat629.vivydoctors.ui.di.modules

import dagger.Module
import dagger.Provides
import dev.sunnat629.vivydoctors.data.doctors.VivyDoctorsApi
import retrofit2.Retrofit

/**
 * @see ApiModule
 * This is an object module which will inject all the api
 * */
@Module
object ApiModule {

    @JvmStatic
    @Provides
    fun provideAuthApi(retrofit: Retrofit): VivyDoctorsApi {
        return retrofit.create(VivyDoctorsApi::class.java)
    }
}
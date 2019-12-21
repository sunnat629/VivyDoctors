package dev.sunnat629.vivydoctors.ui.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.RootApp
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Singleton
    @Binds
    abstract fun provideApplicationContext(context: RootApp): Context
    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideSharedPreference(context: Context): SharedPreferences {
            return context.let {
                it.getSharedPreferences(it.getString(R.string.app_name), Context.MODE_PRIVATE)
            }
        }
    }
}
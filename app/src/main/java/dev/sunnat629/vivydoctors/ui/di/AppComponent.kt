package dev.sunnat629.vivydoctors.ui.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.sunnat629.vivydoctors.RootApp
import dev.sunnat629.vivydoctors.ui.di.modules.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        RemoteModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent : AndroidInjector<RootApp> {

    @Suppress("DEPRECATION")
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<RootApp>()
}
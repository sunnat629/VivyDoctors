package dev.sunnat629.vivydoctors.ui.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.sunnat629.vivydoctors.RootApp
import dev.sunnat629.vivydoctors.ui.di.modules.*
import javax.inject.Singleton

/**
 * This is the component interface where we have included all the modules for inject
 *
 * @see AndroidSupportInjectionModule for more details
 * @see ActivityModule for more details
 * @see FragmentModule for more details
 * @see ViewModelModule for more details
 * @see RemoteModule for more details
 * @see RepositoryModule for more details
 * @see OthersModule for more details
 * */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        RemoteModule::class,
        RepositoryModule::class,
        OthersModule::class
    ]
)
interface AppComponent : AndroidInjector<RootApp> {

    @Suppress("DEPRECATION")
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<RootApp>()
}
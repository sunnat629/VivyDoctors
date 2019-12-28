package dev.sunnat629.vivydoctors.ui.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.sunnat629.vivydoctors.ui.base.ViewModelFactory
import dev.sunnat629.vivydoctors.ui.di.ViewModelKey
import dev.sunnat629.vivydoctors.ui.main.MainViewModel

/**
 * @see RepositoryModule
 * This is an abstract class module which will inject all the repositories
 * */
@Module
abstract class ViewModelModule {

    /**
     * @see ViewModelFactory for more details
     * */
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}
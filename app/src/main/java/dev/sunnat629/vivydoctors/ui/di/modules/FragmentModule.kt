package dev.sunnat629.vivydoctors.ui.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.sunnat629.vivydoctors.ui.main.MainFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment
}
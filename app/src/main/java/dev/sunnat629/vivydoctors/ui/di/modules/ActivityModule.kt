package dev.sunnat629.vivydoctors.ui.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.sunnat629.vivydoctors.ui.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}
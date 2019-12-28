package dev.sunnat629.vivydoctors.ui.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.sunnat629.vivydoctors.ui.main.MainActivity

/**
 * @see ActivityModule
 * This is an abstract class module which will inject all the activities
 * */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}
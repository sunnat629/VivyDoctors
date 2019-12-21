package dev.sunnat629.vivydoctors

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.sunnat629.vivydoctors.ui.di.DaggerAppComponent


class RootApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out RootApp> {
        return DaggerAppComponent.builder().create(this)
    }
}
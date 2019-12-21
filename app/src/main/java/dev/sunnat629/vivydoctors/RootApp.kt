package dev.sunnat629.vivydoctors

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.sunnat629.vivydoctors.ui.di.DaggerAppComponent
import timber.log.Timber


class RootApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out RootApp> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // todo initiate the Firebase or Fabric crashlytics.
        }
    }
}
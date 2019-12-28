package dev.sunnat629.vivydoctors

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dev.sunnat629.vivydoctors.ui.di.DaggerAppComponent
import timber.log.Timber

/**
 * Base class for maintaining global application state. You can provide your own
 * implementation by creating a subclass and specifying the fully-qualified name
 * of this subclass as the <code>"android:name"</code> attribute in your
 * AndroidManifest.xml
 * */
class RootApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out RootApp> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
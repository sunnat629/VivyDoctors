package dev.sunnat629.vivydoctors.ui.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.sunnat629.vivydoctors.ui.main.DoctorDetailsFragment
import dev.sunnat629.vivydoctors.ui.main.DoctorListFragment
import dev.sunnat629.vivydoctors.ui.main.DoctorSearchFragment
import dev.sunnat629.vivydoctors.ui.main.RecentDoctorsFragment

/**
 * @see FragmentModule
 * This is an abstract class module which will inject all the fragments
 * */
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun doctorListFragment(): DoctorListFragment

    @ContributesAndroidInjector
    abstract fun doctorDetailsFragment(): DoctorDetailsFragment

    @ContributesAndroidInjector
    abstract fun recentDoctorsFragment(): RecentDoctorsFragment

    @ContributesAndroidInjector
    abstract fun doctorSearchFragment(): DoctorSearchFragment
}
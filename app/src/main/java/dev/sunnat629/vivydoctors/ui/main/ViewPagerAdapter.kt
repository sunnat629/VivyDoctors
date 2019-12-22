package dev.sunnat629.vivydoctors.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import dev.sunnat629.vivydoctors.R

class ViewPagerAdapter(
    private val context: Context,
    supportFragmentManager: FragmentManager
) : FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> DoctorListFragment()
            1 -> RecentDoctorsFragment()
            else -> DoctorListFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }

    companion object {
        private val TAB_TITLES = arrayOf(
            R.string.nav_all_doctor,
            R.string.nav_recent_doctors
        )
    }
}
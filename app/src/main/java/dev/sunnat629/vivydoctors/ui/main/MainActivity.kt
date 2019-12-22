package dev.sunnat629.vivydoctors.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_tab_items.*
import timber.log.Timber

class MainActivity : BaseActivity<MainViewModel>(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    override val layoutResId: Int = R.layout.activity_main

    override val screenName: String? = MainActivity::class.java.simpleName

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun onInitialize(instance: Bundle?, viewModel: MainViewModel) {
        initNavControl()
        initTabLayouts()
        initObservers()
    }

    private fun initNavControl() {
//        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    private fun initObservers() {
        viewModel.getNetworkState().observe(this, Observer {
        })

        viewModel.getInitialLoad().observe(this, Observer {
        })
    }

    private fun initTabLayouts() {
        val tabsPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        saViewPager.adapter = tabsPagerAdapter
        tabLayout.setupWithViewPager(saViewPager)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.doctorListFragment -> {
                Timber.tag("ASDF").d("doctorListFragment")
//                navController.navigate(R.id.doctorListFragment)
                true
            }
            R.id.doctorDetailsFragment -> {
//                navController.navigate(R.id.doctorDetailsFragment)
                Timber.tag("ASDF").d("doctorDetailsFragment")
                true
            }
            R.id.recentDoctorsFragment -> {
//                navController.navigate(R.id.recentDoctorsFragment)
                Timber.tag("ASDF").d("recentDoctorsFragment")
                true
            }
            else -> false
        }
    }
}
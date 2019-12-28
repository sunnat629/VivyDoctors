package dev.sunnat629.vivydoctors.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.data.utils.Status
import dev.sunnat629.vivydoctors.ui.base.BaseActivity
import dev.sunnat629.vivydoctors.ui.utils.LoggingTags.MAIN_ACTIVITY
import dev.sunnat629.vivydoctors.ui.utils.showIf
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_toolbar.*
import timber.log.Timber

class MainActivity : BaseActivity<MainViewModel>(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController

    override val layoutResId: Int = R.layout.activity_main

    override val screenName: String? = MainActivity::class.java.simpleName

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun onInitialize(instance: Bundle?, viewModel: MainViewModel) {
        initNavControl()
        initObservers()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    private fun initNavControl() {
        navController = Navigation.findNavController(this, R.id.navHostFragment)
        bottomNavigation.setupWithNavController(navController)
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    private fun initObservers() {
        viewModel.getNetworkState().observe(this, Observer {
            when(it.status){
                Status.LOADING, Status.LOADED -> {}
                Status.FAILED, Status.NO_INTERNET -> showError(it.message)
            }
        })

        viewModel.getInitialLoad().observe(this, Observer {
            networkLoadingProgressBar.showIf(it.status == Status.LOADING)
            when(it.status){
                Status.LOADING, Status.LOADED -> {}
                Status.FAILED, Status.NO_INTERNET -> showError(it.message)
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.doctorListFragment -> {
                Timber.tag(MAIN_ACTIVITY).d("doctorListFragment")
                navController.navigate(R.id.doctorListFragment)
                true
            }
            R.id.recentDoctorsFragment -> {
                navController.navigate(R.id.recentDoctorsFragment)
                Timber.tag(MAIN_ACTIVITY).d("recentDoctorsFragment")
                true
            }
            else -> false
        }
    }
}
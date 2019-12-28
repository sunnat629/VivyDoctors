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
import kotlinx.android.synthetic.main.content_toolbar.*
import timber.log.Timber

class MainActivity : BaseActivity<MainViewModel>(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController

    override val layoutResId: Int = R.layout.activity_main

    override val screenName: String? = MainActivity::class.java.simpleName

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun onInitialize(instance: Bundle?, viewModel: MainViewModel) {
        initToolbar()
        initNavControl()
        initObservers()
    }

    private fun initToolbar() {
        toolbar.apply {
            title = resources.getString(R.string.app_name)
            setNavigationOnClickListener {
                finish()
            }
        }
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
        })

        viewModel.getInitialLoad().observe(this, Observer {
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.doctorListFragment -> {
                navController.navigate(R.id.doctorListFragment)
                true
            }
            R.id.recentDoctorsFragment -> {
                navController.navigate(R.id.recentDoctorsFragment)
                Timber.tag("ASDF").d("recentDoctorsFragment")
                true
            }

            R.id.doctorDetailsFragment -> {
                navController.navigate(R.id.doctorDetailsFragment)
                true
            }
            else -> false
        }
    }
}
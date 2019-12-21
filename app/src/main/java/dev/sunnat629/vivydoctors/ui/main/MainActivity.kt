package dev.sunnat629.vivydoctors.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsEntity
import dev.sunnat629.vivydoctors.ui.base.BaseActivity
import dev.sunnat629.vivydoctors.ui.main.adapters.DoctorsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity<MainViewModel>(),
    BottomNavigationView.OnNavigationItemSelectedListener{


    private lateinit var navController: NavController
    private lateinit var bottomNavigation: BottomNavigationView

    private var selectedTab = 0

    override val layoutResId: Int = R.layout.activity_main

    override val screenName: String? = MainActivity::class.java.simpleName

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun onInitialize(instance: Bundle?, viewModel: MainViewModel) {
        initNavControl()
        initObservers()
    }

    private fun initNavControl() {
        navController = Navigation.findNavController(this, R.id.navHostFragment)
        bottomNavigation = findViewById(R.id.bottomNavigation)
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
            R.id.doctorDetailsFragment -> {
                navController.navigate(R.id.doctorDetailsFragment)
                true
            }
            R.id.recentDoctorsFragment -> {
                navController.navigate(R.id.recentDoctorsFragment)
                true
            }
            else -> false
        }
    }
}
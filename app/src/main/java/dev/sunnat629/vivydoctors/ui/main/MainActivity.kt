package dev.sunnat629.vivydoctors.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.ui.base.BaseActivity
import timber.log.Timber

class MainActivity : BaseActivity<MainViewModel>() {

    override val layoutResId: Int = R.layout.activity_main

    override val screenName: String? = MainActivity::class.java.simpleName

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun onInitialize(instance: Bundle?, viewModel: MainViewModel) {
        initObservers()
    }

    private fun initObservers() {
        viewModel.getNetworkState().observe(this, Observer {
            Timber.tag("ASDF").d(it.toString())
        })
        viewModel.getInitialLoad().observe(this, Observer {
            Timber.tag("ASDF").d(it.toString())
        })
        viewModel.doctorsList.observe(this, Observer {
            Timber.tag("ASDF").d(it.size.toString())
        })
    }
}
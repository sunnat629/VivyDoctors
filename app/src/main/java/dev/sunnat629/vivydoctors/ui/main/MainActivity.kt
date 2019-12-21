package dev.sunnat629.vivydoctors.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsEntity
import dev.sunnat629.vivydoctors.ui.base.BaseActivity
import dev.sunnat629.vivydoctors.ui.main.adapters.DoctorsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity<MainViewModel>() {

    private val doctorsAdapter by lazy {
        DoctorsAdapter(
            { singleDoctor ->
                onDoctorClick(singleDoctor)
            },
            {
                retryCallback()
            }
        )
    }

    override val layoutResId: Int = R.layout.activity_main

    override val screenName: String? = MainActivity::class.java.simpleName

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun onInitialize(instance: Bundle?, viewModel: MainViewModel) {
        initObservers()
        initRecyclerView()
    }


    private fun initObservers() {
        viewModel.getNetworkState().observe(this, Observer {
            Timber.tag("ASDF").i("getNetworkState")
        })
        viewModel.getInitialLoad().observe(this, Observer {
            Timber.tag("ASDF").e("getInitialLoad")
        })
        viewModel.doctorsList.observe(this, Observer {
            Timber.tag("ASDF").e("${it.size}")

            doctorsAdapter.submitList(it)
        })
    }

    private fun initRecyclerView() {
        doctorRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = doctorsAdapter
        }
    }


    private fun retryCallback() {
        Timber.tag("ASDF").e("retryCallback")
    }

    private fun onDoctorClick(singleDoctor: DoctorsEntity) {
        Timber.tag("ASDF").d(singleDoctor.toString())
    }
}
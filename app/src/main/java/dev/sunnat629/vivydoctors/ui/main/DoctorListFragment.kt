package dev.sunnat629.vivydoctors.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.data.utils.Status
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsEntity
import dev.sunnat629.vivydoctors.ui.base.BaseFragment
import dev.sunnat629.vivydoctors.ui.main.adapters.DoctorsAdapter
import dev.sunnat629.vivydoctors.ui.utils.showIf
import kotlinx.android.synthetic.main.fragment_doctors.*
import timber.log.Timber

class DoctorListFragment : BaseFragment<MainViewModel>() {

    private val doctorsAdapter by lazy {
        DoctorsAdapter { singleDoctor ->
            onDoctorClick(singleDoctor)
        }
    }

    override val layoutResId: Int = R.layout.fragment_doctors

    override val screenName: String? = DoctorListFragment::class.java.simpleName

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun onInitialize(instance: Bundle?, viewModel: MainViewModel) {
        initUI()
        initRecyclerView()
        initObservers()
    }

    private fun initUI() {
    }


    private fun initRecyclerView() {
        doctorRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = doctorsAdapter
        }
    }

    private fun initObservers() {
        viewModel.getNetworkState().observe(this, Observer {
            loadingProgressBar.showIf(it.status == Status.LOADING)
        })

        viewModel.getInitialLoad().observe(this, Observer {
            loadingProgressBar.showIf(it.status == Status.LOADING)
        })

        viewModel.doctorsList.observe(this, Observer {
            doctorsAdapter.submitList(it)
        })
    }


    private fun onDoctorClick(singleDoctor: DoctorsEntity) {
        Timber.tag("ASDF").d(singleDoctor.toString())
    }
}
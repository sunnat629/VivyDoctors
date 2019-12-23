package dev.sunnat629.vivydoctors.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.data.utils.Status
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsEntity
import dev.sunnat629.vivydoctors.ui.main.adapters.DoctorsAdapter
import dev.sunnat629.vivydoctors.ui.utils.showIf
import kotlinx.android.synthetic.main.fragment_doctors.*
import timber.log.Timber
import javax.inject.Inject

class DoctorListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val doctorsAdapter by lazy {
        DoctorsAdapter { singleDoctor ->
            onDoctorClick(singleDoctor)
        }
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_doctors, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(MainViewModel::class.java)
        onInitialize()
    }

    private fun onInitialize() {
        initRecyclerView()
        initObservers()
    }

    private fun initRecyclerView() {
        doctorRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = doctorsAdapter
        }
    }

    private fun initObservers() {
        viewModel.getNetworkState().observe(viewLifecycleOwner, Observer {
            loadingProgressBar.showIf(it.status == Status.LOADING)
        })

        viewModel.getInitialLoad().observe(viewLifecycleOwner, Observer {
            if (it.status == Status.LOADING) {
                shimmerViewContainer.startShimmer()
            } else shimmerViewContainer.hideShimmer()
        })

        viewModel.doctorsList.observe(viewLifecycleOwner, Observer {
            doctorsAdapter.submitList(it)
        })

        viewModel.recentDoctors.observe(viewLifecycleOwner, Observer {
            Timber.tag("ASDF").e("SIZE: ${it.size}")
        })
    }

    override fun onResume() {
        super.onResume()
        shimmerViewContainer.startShimmer()
    }

    override fun onPause() {
        shimmerViewContainer.stopShimmer()
        super.onPause()
    }

    private fun onDoctorClick(singleDoctor: DoctorsEntity) {
        viewModel.addToRecentDoctorList(singleDoctor)
    }
}
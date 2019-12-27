package dev.sunnat629.vivydoctors.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsEntity
import dev.sunnat629.vivydoctors.ui.main.adapters.RecentDoctorsAdapter
import dev.sunnat629.vivydoctors.ui.utils.showIf
import kotlinx.android.synthetic.main.fragment_recent_doctor.*
import timber.log.Timber
import javax.inject.Inject

class RecentDoctorsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    private val recentDoctorsAdapter by lazy {
        RecentDoctorsAdapter { singleDoctor ->
            onDoctorClick(singleDoctor)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recent_doctor, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(MainViewModel::class.java)
        onInitialize()
    }


    private fun onInitialize() {
        initRecyclerView()
        initObservers()
    }

    private fun initRecyclerView() {
        recentDoctorRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = recentDoctorsAdapter
        }
    }

    private fun initObservers() {
        viewModel.recentDoctors.observe(viewLifecycleOwner, Observer {
            Timber.tag("ASDF").e("SIZE: ${it.size}")
            emptyRecentDr.showIf(it.isNullOrEmpty())
            recentDoctorRecyclerView.showIf(!emptyRecentDr.isVisible)
            recentDoctorsAdapter.submitList(it)
        })
    }

    private fun onDoctorClick(singleDoctor: DoctorsEntity) {
        Timber.tag("ASDF").d(singleDoctor.toString())
    }
}
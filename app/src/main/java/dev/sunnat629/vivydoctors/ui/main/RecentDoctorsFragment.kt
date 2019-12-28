package dev.sunnat629.vivydoctors.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.domain.doctors.DoctorsEntity
import dev.sunnat629.vivydoctors.ui.base.BaseFragment
import dev.sunnat629.vivydoctors.ui.main.adapters.RecentDoctorsAdapter
import dev.sunnat629.vivydoctors.ui.utils.showIf
import kotlinx.android.synthetic.main.fragment_recent_doctor.*
import timber.log.Timber

/**
 * @see RecentDoctorsFragment
 *
 * This fragment will show the list of doctors which are seen by the user
 * */
class RecentDoctorsFragment : BaseFragment<MainViewModel, MainActivity>() {

    private val recentDoctorsAdapter by lazy {
        RecentDoctorsAdapter { singleDoctor ->
            onDoctorClick(singleDoctor)
        }
    }

    override val layoutResId: Int = R.layout.fragment_recent_doctor

    override val screenName: String? = context?.resources?.getString(R.string.nav_recent_doctors) ?: String()

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun getParentActivity(): AppCompatActivity = (activity as MainActivity)

    override fun onInitialize(instance: Bundle?) {
        initToolbar()
        initRecyclerView()
        initObservers()
    }

    private fun initToolbar() {
        setupBaseToolbar()
        showToolbarNavBack(false)
    }

    private fun initRecyclerView() {
        recentDoctorRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = recentDoctorsAdapter
        }
    }

    private fun initObservers() {
        viewModel.recentDoctors.observe(viewLifecycleOwner, Observer {
            emptyRecentDr.showIf(it.isNullOrEmpty())
            recentDoctorRecyclerView.showIf(!emptyRecentDr.isVisible)
            recentDoctorsAdapter.submitList(it)
        })
    }


    private fun onDoctorClick(singleDoctor: DoctorsEntity) {
        findNavController().navigate(R.id.action_recentDoctorsFragment_to_doctorDetailsFragment)
        viewModel.addToRecentDoctorList(singleDoctor)
    }
}
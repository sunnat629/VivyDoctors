package dev.sunnat629.vivydoctors.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.data.utils.Status
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsEntity
import dev.sunnat629.vivydoctors.ui.base.BaseFragment
import dev.sunnat629.vivydoctors.ui.main.adapters.DoctorsAdapter
import dev.sunnat629.vivydoctors.ui.utils.showIf
import kotlinx.android.synthetic.main.fragment_doctors.*

class DoctorListFragment : BaseFragment<MainViewModel, MainActivity>() {

    private val doctorsAdapter by lazy {
        DoctorsAdapter { singleDoctor ->
            onDoctorClick(singleDoctor)
        }
    }

    override val layoutResId: Int = R.layout.fragment_doctors

    override val screenName: String? =
        context?.resources?.getString(R.string.nav_all_doctor) ?: String()

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun getParentActivity(): MainActivity = (activity as MainActivity)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.searchDoctors -> {
                    findNavController().navigate(R.id.action_doctorListFragment_to_doctorSearchFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onInitialize(instance: Bundle?) {
        initToolbar()
        initRecyclerView()
        initObservers()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        setupBaseToolbar()
        showToolbarNavBack(false)
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

        viewModel.doctorsList.observe(viewLifecycleOwner, Observer {
            doctorsAdapter.submitList(it)
        })
    }

    private fun onDoctorClick(singleDoctor: DoctorsEntity) {
        findNavController().navigate(R.id.action_doctorListFragment_to_doctorDetailsFragment)
        viewModel.addToRecentDoctorList(singleDoctor)
    }
}
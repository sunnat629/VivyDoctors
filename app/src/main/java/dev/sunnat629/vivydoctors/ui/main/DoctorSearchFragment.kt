package dev.sunnat629.vivydoctors.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsEntity
import dev.sunnat629.vivydoctors.ui.base.BaseFragment
import dev.sunnat629.vivydoctors.ui.main.adapters.SearchedDoctorsAdapter
import dev.sunnat629.vivydoctors.ui.utils.hideKeyboard
import dev.sunnat629.vivydoctors.ui.utils.showIf
import kotlinx.android.synthetic.main.content_search.*
import kotlinx.android.synthetic.main.fragment_doctors_search.*

class DoctorSearchFragment : BaseFragment<MainViewModel, MainActivity>() {

    private val searchedDoctorsAdapter by lazy {
        SearchedDoctorsAdapter { singleDoctor ->
            onDoctorClick(singleDoctor)
        }
    }

    override val layoutResId: Int = R.layout.fragment_doctors_search

    override val screenName: String? =
        context?.resources?.getString(R.string.nav_search_label) ?: String()

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun getParentActivity(): MainActivity = (activity as MainActivity)

    override fun onInitialize(instance: Bundle?) {
        initToolbar()
        initUI()
        initObservers()
    }

    override fun onPause() {
        super.onPause()
        viewModel.resetSearch()
        hideKeyboard()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllDoctorsForSearch()
    }

    private fun initUI() {
        initRecyclerView()
        initSearchInputListener()
        initButtons()
    }

    private fun initButtons() {
        backSearch.setOnClickListener {
            findNavController().popBackStack()
            findNavController().currentDestination
        }

        clearSearch.setOnClickListener {
            doctorSearchEditText.text = null
        }
    }

    private fun initToolbar() {
        removeBaseToolbar()
    }

    private fun initRecyclerView() {
        searchRecyclerView.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = searchedDoctorsAdapter
        }
    }

    private fun initSearchInputListener() {
        doctorSearchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clearSearch.showIf(count > 0)
                doSearch()
            }
        })
    }

    private fun initObservers() {
        viewModel.searchedDoctors.observe(viewLifecycleOwner, Observer {
            searchRecyclerView.showIf(it.isNotEmpty())
            searchNoDoctor.showIf(!searchRecyclerView.isVisible)
            searchedDoctorsAdapter.submitList(it)
        })
    }

    private fun onDoctorClick(singleDoctor: DoctorsEntity) {
        findNavController().navigate(R.id.action_doctorSearchFragment_to_doctorDetailsFragment)
        viewModel.addToRecentDoctorList(singleDoctor)
    }

    private fun doSearch() {
        val query = doctorSearchEditText.text.toString().trim()
        viewModel.setQuery(query)
    }
}
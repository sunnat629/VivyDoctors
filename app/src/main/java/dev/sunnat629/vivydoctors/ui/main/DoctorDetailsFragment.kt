package dev.sunnat629.vivydoctors.ui.main

import android.os.Bundle
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.ui.base.BaseFragment

class DoctorDetailsFragment : BaseFragment<MainViewModel>() {

    override val layoutResId: Int = R.layout.fragment_doctor_details

    override val screenName: String? = DoctorDetailsFragment::class.java.simpleName

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun onInitialize(instance: Bundle?, viewModel: MainViewModel) {
        initToolbar()
        initUI()
        initButtons()
        initObservers()
    }

    private fun initToolbar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initButtons() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun initObservers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
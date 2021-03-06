package dev.sunnat629.vivydoctors.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.domain.doctors.DoctorsEntity
import dev.sunnat629.vivydoctors.ui.base.BaseFragment
import kotlinx.android.synthetic.main.content_doctor_details.*
import kotlinx.android.synthetic.main.content_rating.*
import kotlinx.android.synthetic.main.fragment_doctor_details.*

/**
 * @see DoctorDetailsFragment
 *
 * This fragment will show the details of a selected doctor by the user
 * */
class DoctorDetailsFragment : BaseFragment<MainViewModel, MainActivity>() {

    override val layoutResId: Int = R.layout.fragment_doctor_details

    override val screenName: String? = DoctorDetailsFragment::class.java.simpleName

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun getParentActivity(): AppCompatActivity = (activity as MainActivity)

    override fun onInitialize(instance: Bundle?) {
        initToolbar()
        initObservers()
    }

    private fun initToolbar() {
        setupBaseToolbar()
        showToolbarNavBack(true)
        getBaseToolbar()?.let {
            it.setNavigationOnClickListener {
                findNavController().popBackStack()
                findNavController().currentDestination
            }
        }
    }

    private fun initUI(selectedDoctor: DoctorsEntity?) {

        selectedDoctor?.apply {
            doctorName.text = name
            doctorAddress.text = address
            doctorEmail.text = email
            doctorPhone.text = phoneNumber
            doctorWebsite.text = website
            review.text = reviewCount?.toString() ?: "0"
            ratingBar.rating = rating?.toFloat() ?: 0f

            context?.let {
                Glide.with(it)
                    .asBitmap()
                    .apply(RequestOptions().override(400, 400))
                    .load(photoId)
                    .placeholder(R.drawable.ic_perm_identity)
                    .into(doctorImage)
            }
        }
    }

    private fun initObservers() {
        viewModel.selectedDoctor.observe(viewLifecycleOwner, Observer { selectedDoctor ->
            initUI(selectedDoctor)
        })
    }
}
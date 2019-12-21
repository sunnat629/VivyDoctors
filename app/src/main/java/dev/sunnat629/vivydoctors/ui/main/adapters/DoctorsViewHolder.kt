package dev.sunnat629.vivydoctors.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsEntity
import kotlinx.android.synthetic.main.vh_doctor_list.view.*
import timber.log.Timber

/**
 * DoctorsViewHolder.kt
 * This is a RecyclerView ViewHolder and show each row when the data fetched successfully
 *
 * @param itemView is the view of a single Doctor in the RecyclerView
 * */
class DoctorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(doctorsEntity: DoctorsEntity?) {
        Timber.tag("QWER").d(doctorsEntity.toString())

        doctorsEntity?.apply {
            itemView.doctorName.text = name

//            Glide.with(itemView)
//                .load(it.photoId)
//                .placeholder(R.drawable.ic_launcher_background)
//                .into(itemView.doctorPhoto)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onDoctorClick: (singleDoctor: DoctorsEntity) -> Unit
        ): DoctorsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.vh_doctor_list, parent, false)
            return DoctorsViewHolder(view)
        }
    }
}
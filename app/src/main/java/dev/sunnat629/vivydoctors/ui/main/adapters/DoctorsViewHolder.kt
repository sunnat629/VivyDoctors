package dev.sunnat629.vivydoctors.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsEntity
import kotlinx.android.synthetic.main.content_rating.view.*
import kotlinx.android.synthetic.main.vh_doctor_list.view.*

/**
 * DoctorsViewHolder.kt
 * This is a RecyclerView ViewHolder and show each row when the data fetched successfully
 *
 * @param itemView is the view of a single Doctor in the RecyclerView
 * */
class DoctorsViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(
        doctorsEntity: DoctorsEntity?,
        onDoctorClick: (singleDoctor: DoctorsEntity) -> Unit
    ) {

        doctorsEntity?.apply {
            itemView.doctorName.text = name
            itemView.doctorAddress.text = address
            itemView.review.text = reviewCount?.toString()?: "0"
            itemView.ratingBar.rating = rating?.toFloat()?: 0f

            Glide.with(itemView)
                .asBitmap()
                .apply( RequestOptions().override(200, 200))
                .load(photoId)
                .placeholder(R.drawable.ic_perm_identity)
                .into(itemView.doctorPhoto)

            itemView.doctorRoot.setOnClickListener {
                onDoctorClick.invoke(this)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup
        ): DoctorsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.vh_doctor_list, parent, false)
            return DoctorsViewHolder(view)
        }
    }
}
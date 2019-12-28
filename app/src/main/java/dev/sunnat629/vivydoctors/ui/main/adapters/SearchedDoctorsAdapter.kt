package dev.sunnat629.vivydoctors.ui.main.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.sunnat629.vivydoctors.domain.doctors.DoctorsEntity
import java.util.*

/**
 * @see SearchedDoctorsAdapter
 * This is a ListAdapter which will show in a RecyclerView using the viewHolder
 * */
class SearchedDoctorsAdapter(
    private val onDoctorClick: (singleDoctor: DoctorsEntity) -> Unit
) : ListAdapter<DoctorsEntity, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DoctorsViewHolder.create(
            parent
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DoctorsViewHolder).bindTo(getItem(position), onDoctorClick)
    }

    companion object {

        /**
         * @see DiffCallback is a DiffUtil which is a utility class that can calculate the
         * difference between two lists and output a list of update operations that converts the
         * first list into the second one. * It can be used to calculate updates for a RecyclerView Adapter.
         * */
        private val DiffCallback =
            object : DiffUtil.ItemCallback<DoctorsEntity>() {
                override fun areItemsTheSame(
                    oldItem: DoctorsEntity,
                    newItem: DoctorsEntity
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: DoctorsEntity,
                    newItem: DoctorsEntity
                ): Boolean {
                    return Objects.equals(oldItem, newItem)
                }
            }
    }
}
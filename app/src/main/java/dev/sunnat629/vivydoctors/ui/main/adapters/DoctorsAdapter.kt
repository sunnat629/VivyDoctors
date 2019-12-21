package dev.sunnat629.vivydoctors.ui.main.adapters

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.data.utils.NetworkState
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsEntity
import java.util.*

/**
 * DoctorsAdapter.kt
 * This is a PagedListAdapter which will show in a RecyclerView using two ViewHolders
 * @see DoctorsAdapter for more details
 * @see NetworkStateViewHolder for more details
 *
 * @param retryCallback is a callback which will trigger if the fetch fails.
 * */
class DoctorsAdapter(
    private val onDoctorClick: (singleDoctor: DoctorsEntity) -> Unit,
    private val retryCallback: () -> Unit
) : PagedListAdapter<DoctorsEntity, RecyclerView.ViewHolder>(DiffCallback) {

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            // This layout will show, if data fetched successfully
            R.layout.vh_doctor_list -> DoctorsViewHolder.create(
                parent,
                onDoctorClick
            )

            // This layout will show, if an error occurs during data fetch
            R.layout.vh_network_state -> NetworkStateViewHolder.create(
                parent,
                retryCallback
            )
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.vh_doctor_list -> (holder as DoctorsViewHolder).bindTo(getItem(position))
            R.layout.vh_network_state -> (holder as NetworkStateViewHolder).bindTo(networkState)
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState !== NetworkState.LOADED
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.vh_network_state
        } else {
            R.layout.vh_doctor_list
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    /**
     * Set the current network state to the adapter but this work only after the initial load
     * and the adapter already have list to add new loading raw to it so the initial loading state
     * the activity responsible for handle it
     *
     * @param newNetworkState the new network state
     */
    fun setNetworkState(newNetworkState: NetworkState) {
        if (!currentList.isNullOrEmpty()) {
            currentList?.size.let {
                val previousState = this.networkState
                val hadExtraRow = hasExtraRow()
                this.networkState = newNetworkState
                val hasExtraRow = hasExtraRow()
                if (hadExtraRow != hasExtraRow) {
                    if (hadExtraRow) {
                        notifyItemRemoved(super.getItemCount())
                    } else {
                        notifyItemInserted(super.getItemCount())
                    }
                } else if (hasExtraRow && previousState !== newNetworkState) {
                    notifyItemChanged(itemCount - 1)
                }
            }
        }
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
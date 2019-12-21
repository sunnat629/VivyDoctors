package dev.sunnat629.vivydoctors.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.data.utils.NetworkState
import dev.sunnat629.vivydoctors.data.utils.Status
import kotlinx.android.synthetic.main.content_network_state.view.*

/**
 * NetworkStateViewHolder.kt
 * This is a RecyclerView ViewHolder which will show in the last row if there there any error during
 * fetch data from the server.
 *
 * @param view is the View of the RecyclerView
 * @param retryCallback is a callback which will trigger if the fetch fails.
 * */
class NetworkStateViewHolder(view: View, private val retryCallback: () -> Unit) :
    RecyclerView.ViewHolder(view) {

    init {
        itemView.retryLoadingButton.setOnClickListener { retryCallback() }
    }

    fun bindTo(networkState: NetworkState?) {
        //error message
        itemView.errorMessageTextView.visibility =
            if (networkState?.message != null) View.VISIBLE else View.GONE
        if (networkState?.message != null) {
            itemView.errorMessageTextView.text = networkState.message
        }

        //loading and retry
        itemView.retryLoadingButton.visibility =
            if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
        itemView.loadingProgressBar.visibility =
            if (networkState?.status == Status.LOADING) View.VISIBLE else View.GONE
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStateViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.content_network_state, parent, false)
            return NetworkStateViewHolder(view, retryCallback)
        }
    }
}
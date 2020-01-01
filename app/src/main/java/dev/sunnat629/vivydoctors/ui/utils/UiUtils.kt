package dev.sunnat629.vivydoctors.ui.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import dev.sunnat629.vivydoctors.domain.doctors.DoctorsEntity
import java.util.*

fun View.showIf(should: Boolean) {
    this.visibility = if (should) View.VISIBLE else View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun TextView.clear() {
    this.text = null
}

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard(this.view!!)
}

private fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun <T> MutableLiveData<List<T>>.getRecentDoctors(values: T, size: Int) {
    val list = this.value?.toMutableList() ?: mutableListOf()
    this.value = getRecentDoctors(list, values, size)
}


fun <T> getRecentDoctors(liveDataList: MutableList<T>, values: T, size: Int): List<T> {
    if (liveDataList.contains(values)) liveDataList.remove(values)
    liveDataList.add(values)
    return liveDataList.takeLast(size)
}

fun searchDoctors(list: MutableList<DoctorsEntity>?, modifiedInput: String): List<DoctorsEntity> {
    val searchedDoctorsByName: MutableList<DoctorsEntity> = mutableListOf()

    list?.map { singleDoctor ->
        singleDoctor.name?.let {
            if (it.toLowerCase(Locale.getDefault()).contains(modifiedInput)) {
                searchedDoctorsByName.add(singleDoctor)
            }
        }
    }
    return searchedDoctorsByName
}


fun DataSource.Factory<String, DoctorsEntity>.sortByRating(): DataSource.Factory<String, DoctorsEntity> {
    return mapByPage {
        it.sortedWith(compareByDescending { item -> item.rating })
    }
}
package dev.sunnat629.vivydoctors.ui.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

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

fun <T> MutableLiveData<List<T>>.plusAssign(values: T) {
    val list = this.value?.toMutableList() ?: mutableListOf()
    list.reverse()
    list.remove(values)
    list.add(values)
    this.value = list.takeLast(3).asReversed() // take last 3 items as question asked
}
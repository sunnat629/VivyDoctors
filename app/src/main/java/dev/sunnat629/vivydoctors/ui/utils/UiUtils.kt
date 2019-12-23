package dev.sunnat629.vivydoctors.ui.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData


fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


fun View.showIf(should: Boolean) {
    this.visibility = if (should) View.VISIBLE else View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun TextView.clear() {
    this.text = ""
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard(this.view!!)
}

fun Fragment.showKeyboard() {
    activity?.showKeyboard()
}

fun Activity.hideKeyboard() {
    if (currentFocus == null) View(this) else currentFocus?.let { hideKeyboard(it) }
}

private fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

private fun Context.showKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun CharSequence?.isNotNullOrEmpty(): Boolean {
    return !this.isNullOrEmpty()
}

fun <T> List<T>.isNotNullOrEmpty(): Boolean {
    return this.isNullOrEmpty().not()
}

operator fun <T> MutableLiveData<List<T>>.plusAssign(values: T) {
    val value = this.value?.toMutableList() ?: mutableListOf()
    if (!value.contains(values)) {
        value.add(values)
        this.value = value
    }
}
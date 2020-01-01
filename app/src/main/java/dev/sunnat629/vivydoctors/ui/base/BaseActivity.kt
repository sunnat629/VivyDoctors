package dev.sunnat629.vivydoctors.ui.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import dev.sunnat629.vivydoctors.R
import javax.inject.Inject

/**
 * This is an abstract base class for every activities and it is injected activity and also
 * initialized the viewModel and other activity related field.
 * */
abstract class BaseActivity<MV : BaseViewModel> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: MV

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setLayout(layoutResId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModel())
        onInitialize(savedInstanceState, viewModel)
    }

    protected abstract fun getViewModel(): Class<MV>

    /** Abstract method to supply ViewModel instance into subclass through parameter after onCreate() call*/
    protected abstract fun onInitialize(instance: Bundle?, viewModel: MV)

    @get:LayoutRes
    protected abstract val layoutResId: Int

    protected abstract val screenName: String?

    private fun setLayout(layoutResID: Int) {
        setContentView(layoutResID)

    }

    fun showError(message: String?) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setCancelable(false)
            setMessage(message)
            setTitle(R.string.error_label)
            setPositiveButton(R.string.ok) { alert, _ ->
                alert.dismiss()
            }
        }
        builder.create().show()
    }
}
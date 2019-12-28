package dev.sunnat629.vivydoctors.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity<MV : BaseViewModel> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: MV

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}
package dev.sunnat629.vivydoctors.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import dev.sunnat629.vivydoctors.R
import kotlinx.android.synthetic.main.fragment_base.view.*
import javax.inject.Inject

abstract class BaseFragment<M : BaseViewModel> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: M

    protected lateinit var fragmentContainer: View

    @get:LayoutRes
    protected abstract val layoutResId: Int

    protected abstract val screenName: String?

    protected abstract fun getViewModel(): Class<M>

    /** Abstract method to supply ViewModel instance into subclass through parameter after onCreate() call*/
    protected abstract fun onInitialize(instance: Bundle?, viewModel: M)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentContainer = inflater.inflate(R.layout.fragment_base, null)
        layoutInflater
            .inflate(layoutResId, fragmentContainer.fragment_content_frame)
        setHasOptionsMenu(true)
        return fragmentContainer

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(activity!!, viewModelFactory).get(getViewModel())
        onInitialize(savedInstanceState, viewModel)
    }
}
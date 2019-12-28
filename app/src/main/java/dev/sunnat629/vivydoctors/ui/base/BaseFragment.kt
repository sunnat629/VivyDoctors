package dev.sunnat629.vivydoctors.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import dev.sunnat629.vivydoctors.R
import dev.sunnat629.vivydoctors.ui.utils.gone
import kotlinx.android.synthetic.main.content_toolbar.*
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.fragment_base.view.*
import javax.inject.Inject

abstract class BaseFragment<M : BaseViewModel, C : AppCompatActivity> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: M

    private lateinit var fragmentContainer: View

    private var actionBar: ActionBar? = null

    @get:LayoutRes
    protected abstract val layoutResId: Int

    protected abstract val screenName: String?

    protected abstract fun getViewModel(): Class<M>

    protected abstract fun getParentActivity(): AppCompatActivity

    /** Abstract method to supply ViewModel instance into subclass through parameter after onCreate() call*/
    protected abstract fun onInitialize(instance: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentContainer = inflater.inflate(R.layout.fragment_base, null)
        layoutInflater
            .inflate(layoutResId, fragmentContainer.fragmentBaseContent)
        setHasOptionsMenu(true)
        return fragmentContainer

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(getParentActivity(), viewModelFactory).get(getViewModel())
        onInitialize(savedInstanceState)
    }

    fun setupBaseToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        (activity as AppCompatActivity).supportActionBar?.let {
            actionBar = it
        }
    }

    fun showToolbarTitle(title: String) {
        actionBar?.let {
            it.setDisplayShowTitleEnabled(true)
            it.title = title
        }
    }

    fun showToolbarNavBack(hasShown: Boolean) {
        actionBar?.setDisplayHomeAsUpEnabled(hasShown)
    }

    fun getBaseToolbar(): Toolbar? {
        return toolbar
    }

    fun removeBaseToolbar() {
        contentToolbar?.gone()
    }
}
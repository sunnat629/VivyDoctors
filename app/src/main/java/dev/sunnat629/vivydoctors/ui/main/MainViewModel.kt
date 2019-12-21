package dev.sunnat629.vivydoctors.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dev.sunnat629.vivydoctors.data.utils.NetworkState
import dev.sunnat629.vivydoctors.data.utils.Status
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsEntity
import dev.sunnat629.vivydoctors.ui.base.BaseViewModel
import dev.sunnat629.vivydoctors.ui.datasource.DataSourceFactory
import dev.sunnat629.vivydoctors.ui.utils.DSConstants.INITIAL_LOAD_SIZE
import dev.sunnat629.vivydoctors.ui.utils.DSConstants.PAGE_SIZE
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val dataSourceFactory: DataSourceFactory
) : BaseViewModel() {

    /**
     * This immutable variable contains the PagedList.Config where we mention the loading page size and an
     * initial loading size.
     * */
    private val config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setInitialLoadSizeHint(INITIAL_LOAD_SIZE)
        .setEnablePlaceholders(false)
        .build()

    /**
     * This immutable variable contains the doctor data after fetching from.
     * */
    val doctorsList: LiveData<PagedList<DoctorsEntity>>

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    /**
     * initialize the viewModel where we inject the component and retrieve the doctor data using
     * DataSourceFactory.
     * */
    init {
        doctorsList = LivePagedListBuilder(dataSourceFactory, config).build()
    }

    /**
     * This function is a liveData of the NetworkState which observe the network status of
     * this project.
     * */
    fun getNetworkState(): LiveData<NetworkState> =
        Transformations.switchMap(
            dataSourceFactory.paginationDataSourceLiveData
        ) { it.networkState }

    /**
     * This function is a liveData of the NetworkState which observe the network status of
     * this project.
     * */
    fun getInitialLoad(): LiveData<NetworkState> =
        Transformations.switchMap(
            dataSourceFactory.paginationDataSourceLiveData
        ) { it.initialLoad }

    /**
     * This function will use if the fetched functionality failed somehow in the initial stage or
     * middle of the task.
     * */
    fun retry() {
        dataSourceFactory.paginationDataSourceLiveData.value?.retryAllFailed()
    }

    /**
     * This function will used for reset the list and fetch the fresh data from the server.
     * */
    fun refresh() {
        dataSourceFactory.paginationDataSourceLiveData.value?.invalidate()
    }
}
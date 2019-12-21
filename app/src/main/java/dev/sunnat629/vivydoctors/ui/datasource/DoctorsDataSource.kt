package dev.sunnat629.vivydoctors.ui.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import dev.sunnat629.vivydoctors.data.doctors.VivyDoctorsDataRepository
import dev.sunnat629.vivydoctors.data.utils.NetworkResult
import dev.sunnat629.vivydoctors.data.utils.NetworkState
import dev.sunnat629.vivydoctors.data.utils.NetworkState.Companion.ERROR
import dev.sunnat629.vivydoctors.data.utils.NetworkState.Companion.LOADED
import dev.sunnat629.vivydoctors.data.utils.NetworkState.Companion.LOADING
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsEntity
import dev.sunnat629.vivydoctors.ui.utils.DSConstants.FIRST_PAGE
import dev.sunnat629.vivydoctors.ui.utils.LoggingTags.DATA_S_FACTORY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 *
 * This is a dataSource class which is implemented a DataSource using PageKeyedDataSource.
 * if any developer needs to use data from page N - 1 to load page N, then this PageKeyedDataSource uses.
 *
 * @param scope is the {@linkplain CoroutineScope scope}
 * @param vivyDoctorsRepository is the {@linkplain VivyDoctorsDataRepository repository}
 * */
class DoctorsDataSource(
    private val scope: CoroutineScope,
    private val vivyDoctorsRepository: VivyDoctorsDataRepository
) : PageKeyedDataSource<String, DoctorsEntity>() {

    /**
     * _networkState is a MutableLiveData of NetworkState which will changes based on the network status
     * */
    private val _networkState: MutableLiveData<NetworkState> = MutableLiveData()

    /**
     * networkState is a LiveData of NetworkState which will changes based on {@see _networkState}
     * */
    val networkState: LiveData<NetworkState>
        get() = _networkState

    /**
     * _initialLoad is a MutableLiveData of NetworkState which will changes based on the network status
     * */
    private val _initialLoad: MutableLiveData<NetworkState> = MutableLiveData()

    /**
     * initialLoad is a LiveData of NetworkState which will changes based on {@see _initialLoad}
     * */
    val initialLoad: LiveData<NetworkState>
        get() = _initialLoad

    /**
     * retry will use to trigger this DataSource class again if there is any error during fetch the
     * data from the server
     * */
    private var retry: (() -> Any)? = null

    /**
     * retryAllFailed is public function which will used in viewModel. This is the public version of
     * @see retry
     * */
    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            scope.launch {
                it.invoke()
            }
        }
    }

    private val _nextPage = MutableLiveData<String>(FIRST_PAGE)
    private val nextPage: LiveData<String> = _nextPage

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, DoctorsEntity>
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * This function is called first to initialize a PagedList with data.
     * If it's possible to count the items that can be loaded by the DataSource.
     *
     * @param params is LoadInitialParams: Parameters for initial load, including requested load size.
     * @param callback is LoadInitialCallback: Callback that receives initial load data.
     * */
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, DoctorsEntity>
    ) {
        setInitNetworkState(LOADING)
        Timber.tag(DATA_S_FACTORY).d("loadInitial ${params.requestedLoadSize}")

        scope.launch {
            when (val result = vivyDoctorsRepository.getAllDoctors(FIRST_PAGE)) {

                is NetworkResult.Success -> {
                    Timber.tag(DATA_S_FACTORY).d("loadInitial $FIRST_PAGE")
                    _nextPage.postValue("$FIRST_PAGE-${result.data.lastKey}")

                    callback.onResult(
                        result.data.doctors ?: emptyList(),
                        null,
                        nextPage.value
                    )
                    setInitNetworkState(LOADED)
                }

                is NetworkResult.Error -> {
                    retry = {
                        loadInitial(params, callback)
                    }
                    setInitNetworkState(ERROR(result.exception))
                }

                is NetworkResult.NoInternet -> {
                    retry = {
                        loadInitial(params, callback)
                    }
                    setInitNetworkState(ERROR(result.message))
                }

                is NetworkResult.RateLimit -> {
                    retry = {
                        loadInitial(params, callback)
                    }
                    setInitNetworkState(ERROR(result.message))
                }
            }
        }
    }

    /**
     * It's valid to return a different list size than the page size if it's easier,
     * e.g. if your backend defines page sizes.
     * It is generally safer to increase the number loaded than reduce.
     *
     * @param params is LoadParams: Parameters for the load, including the key for the new page, and requested load size.
     * @param callback is LoadCallback: Callback that receives loaded data.
     * */
    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, DoctorsEntity>
    ) {

        scope.launch {

            when (val result = vivyDoctorsRepository.getAllDoctors(nextPage.value!!)) {

                is NetworkResult.Success -> {
                    callback.onResult(result.data.doctors ?: emptyList(), nextPage.value)
                    _networkState.postValue(LOADED)
                }

                is NetworkResult.Error -> {
                    retry = {
                        loadAfter(params, callback)
                    }
                    _networkState.postValue(ERROR(result.exception))
                }

                is NetworkResult.NoInternet -> {
                    retry = {
                        loadAfter(params, callback)
                    }
                    _networkState.postValue(ERROR(result.message))
                }

                is NetworkResult.RateLimit -> {
                    retry = {
                        loadAfter(params, callback)
                    }
                    _networkState.postValue(ERROR(result.message))
                }
            }
        }
    }

    /**
     * This function sets the network status of
     * @see _networkState value and
     * @see _initialLoad value
     * */
    private fun setInitNetworkState(networkState: NetworkState) {
        _networkState.postValue(networkState)
        _initialLoad.postValue(networkState)
    }
}
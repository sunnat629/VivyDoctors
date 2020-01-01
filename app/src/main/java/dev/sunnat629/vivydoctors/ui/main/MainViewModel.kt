package dev.sunnat629.vivydoctors.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dev.sunnat629.vivydoctors.data.utils.NetworkState
import dev.sunnat629.vivydoctors.domain.doctors.DoctorsEntity
import dev.sunnat629.vivydoctors.ui.base.BaseViewModel
import dev.sunnat629.vivydoctors.ui.datasource.DataSourceFactory
import dev.sunnat629.vivydoctors.ui.utils.DSConstants.INITIAL_LOAD_SIZE
import dev.sunnat629.vivydoctors.ui.utils.DSConstants.PAGE_SIZE
import dev.sunnat629.vivydoctors.ui.utils.DSConstants.RECENT_DOCTOR_LIST_SIZE
import dev.sunnat629.vivydoctors.ui.utils.getRecentDoctors
import dev.sunnat629.vivydoctors.ui.utils.searchDoctors
import dev.sunnat629.vivydoctors.ui.utils.sortByRating
import java.util.*
import javax.inject.Inject

/**
 * @see MainViewModel
 * This is the viewModel class and for this project, all the {@code fragments}
 * and {@code activities} to observers all the data.
 * */
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
        .setEnablePlaceholders(true)
        .build()

    /**
     * This immutable variable contains the doctor data after fetching from.
     * */
    val doctorPagedList: LiveData<PagedList<DoctorsEntity>>


    /**
     * This mutable and immutable list contains the doctor list which are from the doctor selection by the user
     * */
    private val _recentDoctors = MutableLiveData<List<DoctorsEntity>>()
    val recentDoctors: LiveData<List<DoctorsEntity>> = _recentDoctors

    /**
     * This mutable and immutable data contains the selected doctor which is selected by the user
     * */
    private val _selectedDoctor = MutableLiveData<DoctorsEntity>()
    val selectedDoctor: LiveData<DoctorsEntity> = _selectedDoctor

    /**
     * This mutable and immutable list contains the doctor list which are from the searched data by the user
     * */
    private val _searchedDoctors = MutableLiveData<List<DoctorsEntity>>()
    val searchedDoctors: LiveData<List<DoctorsEntity>> = _searchedDoctors

    /**
     * initialize the viewModel where we inject the component and retrieve the doctor data using
     * DataSourceFactory.
     * */
    init {
        doctorPagedList = LivePagedListBuilder(dataSourceFactory.sortByRating(), config).build()
    }

    /**
     * This function is a liveData of the NetworkState which observe the network status of
     * this project.
     * */
    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap(
        dataSourceFactory.paginationDataSourceLiveData
    ) { it.networkState }

    /**
     * This function is a liveData of the NetworkState which observe the network status of
     * this project.
     * */
    fun getInitialLoad(): LiveData<NetworkState> = Transformations.switchMap(
        dataSourceFactory.paginationDataSourceLiveData
    ) { it.initialLoad }

    fun addToRecentDoctorList(selectedDoctor: DoctorsEntity) {
        _selectedDoctor.postValue(selectedDoctor)
        _recentDoctors.getRecentDoctors(selectedDoctor, RECENT_DOCTOR_LIST_SIZE)
    }

    fun setQuery(originalInput: String) {
        val modifiedInput = originalInput.toLowerCase(Locale.getDefault())
        val searchedDoctorsByName =searchDoctors(doctorPagedList.value, modifiedInput).sortedByDescending { it.rating }
        _searchedDoctors.postValue(searchedDoctorsByName)
    }

    fun getAllDoctorsForSearch() {
        _searchedDoctors.postValue(doctorPagedList.value?.sortedByDescending { it.rating })
    }

    fun resetSearch() {
        _searchedDoctors.postValue(emptyList())
    }
}
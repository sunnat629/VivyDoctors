package dev.sunnat629.vivydoctors.ui.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import dev.sunnat629.vivydoctors.data.doctors.VivyDoctorsDataRepository
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsListDtoEntity
import kotlinx.coroutines.CoroutineScope

/**
 * A simple DataSourceFactory which also provides a way to observe the last created DataSourceFactory.
 * This allows us to channel its network request status etc back to the UI. See the Listing creation
 * in the Repository class.
 */
class DataSourceFactory(
    private val scope: CoroutineScope,
    private val doctorsRepository: VivyDoctorsDataRepository
) : DataSource.Factory<Int, DoctorsListDtoEntity>() {

    val paginationDataSourceLiveData = MutableLiveData<DoctorsDataSource>()

    override fun create(): DataSource<Int, DoctorsListDtoEntity> {
        val usersDataSource = DoctorsDataSource(scope, doctorsRepository)
        paginationDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource
    }
}
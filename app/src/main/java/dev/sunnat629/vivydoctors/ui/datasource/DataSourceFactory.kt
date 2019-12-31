package dev.sunnat629.vivydoctors.ui.datasource

import androidx.arch.core.util.Function
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import dev.sunnat629.vivydoctors.data.doctors.VivyDoctorsDataRepository
import dev.sunnat629.vivydoctors.domain.doctors.DoctorsEntity
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

/**
 * A simple DataSourceFactory which also provides a way to observe the last created DataSourceFactory.
 * This allows us to channel its network request status etc back to the UI. See the Listing creation
 * in the Repository class.
 */
class DataSourceFactory @Inject constructor(
    private val scope: CoroutineScope,
    private val doctorsRepository: VivyDoctorsDataRepository
) : DataSource.Factory<String, DoctorsEntity>() {

    val paginationDataSourceLiveData = MutableLiveData<DoctorsDataSource>()

    override fun create(): DataSource<String, DoctorsEntity> {
        val usersDataSource = DoctorsDataSource(scope, doctorsRepository)
        paginationDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource
    }

    override fun <ToValue : Any?> mapByPage(function: Function<MutableList<DoctorsEntity>, MutableList<ToValue>>): DataSource.Factory<String, ToValue> {
        return super.mapByPage(function)
    }
}
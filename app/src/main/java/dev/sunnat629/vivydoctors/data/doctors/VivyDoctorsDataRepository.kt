package dev.sunnat629.vivydoctors.data.doctors

import dev.sunnat629.vivydoctors.data.utils.NetworkResult
import dev.sunnat629.vivydoctors.domain.doctors.VivyDoctorsRepository
import dev.sunnat629.vivydoctors.domain.doctors.DoctorsListDtoEntity
import dev.sunnat629.vivydoctors.ui.base.BaseRepository
import javax.inject.Inject

/**
 * VivyDoctorsDataRepository.kty.kt
 *
 * @param vivyDoctorsApi is an injected {@linkplain VivyDoctorsApi service}
 * */
class VivyDoctorsDataRepository @Inject constructor(
    private val vivyDoctorsApi: VivyDoctorsApi,
    private val mapper: DoctorsListDtoMapper
) :
    BaseRepository(), VivyDoctorsRepository {

    /**
     * This suspending function returns the json
     *
     * @param doctors is the key-url using to fetch next doctors data.
     * */
    override suspend fun getAllDoctors(doctors: String): NetworkResult<DoctorsListDtoEntity> =
        safeApiCall({ vivyDoctorsApi.getDoctorsList(doctors) }, mapper)
}
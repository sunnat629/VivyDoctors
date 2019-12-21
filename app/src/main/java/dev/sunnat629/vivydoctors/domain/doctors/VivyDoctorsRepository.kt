package dev.sunnat629.vivydoctors.domain.doctors

import dev.sunnat629.vivydoctors.data.utils.NetworkResult
import dev.sunnat629.vivydoctors.domain.doctors.doctorList.DoctorsListDtoEntity

/**
 * VivyDoctorsDataRepository.kt
 * */
interface VivyDoctorsRepository {

    suspend fun getAllDoctors(doctors: String): NetworkResult<DoctorsListDtoEntity>
}
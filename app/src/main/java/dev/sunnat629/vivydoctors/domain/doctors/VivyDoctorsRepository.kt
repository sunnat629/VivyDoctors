package dev.sunnat629.vivydoctors.domain.doctors

import dev.sunnat629.vivydoctors.data.doctors.DoctorsListModel
import dev.sunnat629.vivydoctors.data.utils.NetworkResult

/**
 * VivyDoctorsDataRepository.kt
 * */
interface VivyDoctorsRepository {

    suspend fun getAllDoctors(doctors: String): NetworkResult<DoctorsListModel>
}
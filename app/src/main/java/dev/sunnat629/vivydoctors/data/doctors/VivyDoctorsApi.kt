package dev.sunnat629.vivydoctors.data.doctors

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VivyDoctorsApi {

    @GET("{doctors}.json")
    suspend fun getDoctorsList(@Path("doctors") doctors: String): Response<DoctorsListDto>
}
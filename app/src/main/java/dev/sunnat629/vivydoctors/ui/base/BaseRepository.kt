package dev.sunnat629.vivydoctors.ui.base

import dev.sunnat629.vivydoctors.data.utils.NetworkResult
import dev.sunnat629.vivydoctors.ui.utils.LoggingTags.BASE_REPOSITORY
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

/**
 * BaseRepository.kt
 * This class will be extended by all other repository classes.
 * */
open class BaseRepository {

    /**
     * @see safeApiCall is a top-level suspending function for the sake of adding the try/catch to every
     * Network request. It triggers the network requests.
     *
     * @param call is a suspending Response.
     * */
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): NetworkResult<T> {

        return try {
            if (call.invoke().isSuccessful) {
                Timber.tag(BASE_REPOSITORY).d("Data fetched Successfully.")
                NetworkResult.Success(call.invoke().body()!!)
            } else {
                Timber.tag(BASE_REPOSITORY).e("${call.invoke().code()}: ${call.invoke().message()}")
                NetworkResult.Error(call.invoke().message())
            }
        } catch (ioException: IOException) {
            Timber.tag(BASE_REPOSITORY).e("ioException: ${ioException.message}")

            NetworkResult.NoInternet("${ioException.message}")  // If the internet connection is no available, it will return NetworkResult.NoInternet with the ioException message
        }
    }
}
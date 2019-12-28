package dev.sunnat629.vivydoctors.data.utils


/**
 * NetworkResult.kt
 * This sealed classes to wrap-up the response in Success, Error, RateLimit and NoInternet case.
 * */
sealed class NetworkResult<out E : Any?> {

    /**
     * This class will call after fetching the data from server successfully.
     * It contains the data.
     * @param E is the container of the data from the server.
     * */
    data class Success<out E : Any?>(val data: E) : NetworkResult<E>()

    /**
     * This class will call if there is any error during fetching the data from server.
     * It contains the error message.
     * @param exception is the message why the fetch failed
     * */
    data class Error(val exception: String) : NetworkResult<Nothing>()

    /**
     * This class will call if the application can't connect with the internet
     * It contains the error message.
     * @param message mentions that the application can't connect with the internet.
     * */
    data class NoInternet(val message: String) : NetworkResult<Nothing>()

    /**
     * This class will call if the application exceeds its limit.
     * It contains the error message.
     * @param message mentions that the application exceeds its limit.
     * */
    data class Limits(val message: String) : NetworkResult<Nothing>()
}
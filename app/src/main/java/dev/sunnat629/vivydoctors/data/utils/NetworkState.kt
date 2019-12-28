package dev.sunnat629.vivydoctors.data.utils

/**
 * NetworkState.kt
 * This data class contains the network status with a message if available
 * */
data class NetworkState(
    val status: Status,
    val message: String? = null
) {

    companion object {
        val LOADED = NetworkState(Status.LOADED)
        val LOADING = NetworkState(Status.LOADING)
        fun ERROR(status: Status, message: String?) = NetworkState(status, message)
    }
}

/**
 * Status.kt
 * This enum class contains the network status
 * */
enum class Status {
    LOADING,
    LOADED,
    FAILED,
    NO_INTERNET,
    LIMITS
}
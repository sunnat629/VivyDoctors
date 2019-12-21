package dev.sunnat629.vivydoctors.ui.utils

/**
 * DSConstants.kt
 * This object class contains all the constant values of this project.
 * */
object DSConstants {
    const val BASE_URL = "https://vivy.com/interviews/challenges/android/"

    const val FIRST_PAGE = "doctors"
    const val CONNECT_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L
    const val PAGE_SIZE = 10
    const val INITIAL_LOAD_SIZE = PAGE_SIZE.times(2)
}

/**
 * LoggingTags.kt
 * This object class contains all the constant values of the logger tag.
 * */
object LoggingTags {
    const val REPOSITORY = "REPOSITORY"
    const val MAIN_ACTIVITY = "MAIN_ACTIVITY"
    const val BASE_REPOSITORY = "BASE_REPOSITORY"
    const val DATA_S_FACTORY = "DATA_S_FACTORY"
    const val DATA_SOURCE = "DATA_SOURCE"
    const val VIEW_MODEL = "VIEW_MODEL"
}
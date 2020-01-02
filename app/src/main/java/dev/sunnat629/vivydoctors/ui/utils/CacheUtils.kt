package dev.sunnat629.vivydoctors.ui.utils

import android.content.Context
import dev.sunnat629.vivydoctors.ui.utils.LoggingTags.CACHE_UTILS
import timber.log.Timber
import java.io.File


object CacheUtils {
    fun deleteCache(context: Context) {
        try {
            val dir: File = context.cacheDir
            val isDeleted = deleteDir(dir)
            Timber.tag(CACHE_UTILS).d("Cache is deleted: $isDeleted")
        } catch (e: Exception) {
            Timber.tag(CACHE_UTILS).e(e.message)
        }
    }

    private fun deleteDir(dir: File?): Boolean {
        return if (dir != null && dir.isDirectory) {
            val children = dir.list()?.toList()
            children?.map {
                val success = deleteDir(File(dir, it))
                return !success
            }
            dir.delete()
        } else if (dir != null && dir.isFile) {
            dir.delete()
        } else {
            false
        }
    }
}
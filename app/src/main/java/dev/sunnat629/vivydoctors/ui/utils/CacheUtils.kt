package dev.sunnat629.vivydoctors.ui.utils

import android.content.Context
import java.io.File


class CacheUtils {
    fun deleteCache(context: Context) {
        try {
            val dir: File = context.cacheDir
            deleteDir(dir)
        } catch (e: Exception) {
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
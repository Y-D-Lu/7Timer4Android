package cn.arsenals.seventimer.utils

import android.util.Log
import cn.arsenals.seventimer.BuildConfig

object LogUtil {
    @JvmField val enabled = BuildConfig.DEBUG

    private fun convertTag(tag: String): String {
        return "ARSENALS_SOS : $tag"
    }

    fun v(tag: String, msg: String) {
        if (enabled) {
            Log.v(convertTag(tag), msg)
        }
    }

    fun v(tag: String, msg: String, tr: Throwable) {
        if (enabled) {
            Log.v(convertTag(tag), msg, tr)
        }
    }

    fun d(tag: String, msg: String) {
        if (enabled) {
            Log.d(convertTag(tag), msg)
        }
    }

    fun d(tag: String, msg: String, tr: Throwable) {
        if (enabled) {
            Log.d(convertTag(tag), msg, tr)
        }
    }

    fun i(tag: String, msg: String) {
        Log.i(convertTag(tag), msg)
    }

    fun i(tag: String, msg: String, tr: Throwable) {
        Log.i(convertTag(tag), msg, tr)
    }

    fun w(tag: String, msg: String) {
        Log.w(convertTag(tag), msg)
    }

    fun w(tag: String, msg: String, tr: Throwable) {
        Log.w(convertTag(tag), msg, tr)
    }

    fun e(tag: String, msg: String) {
        Log.e(convertTag(tag), msg)
    }

    fun e(tag: String, msg: String, tr: Throwable) {
        Log.e(convertTag(tag), msg, tr)
    }

    fun wtf(tag: String, msg: String) {
        Log.wtf(convertTag(tag), msg)
    }

    fun wtf(tag: String, tr: Throwable) {
        Log.wtf(convertTag(tag), tr)
    }

    fun wtf(tag: String, msg: String, tr: Throwable) {
        Log.wtf(convertTag(tag), msg, tr)
    }
}

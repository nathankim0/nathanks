package com.jinyeob.nathanks.log.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.jinyeob.nathanks.common.log.repository.LogStore.getLogFiles
import com.jinyeob.nathanks.preferences.repository.PreferencesRepository
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.jinyeob.nathanks.common.log.repository.LogLevel
import com.jinyeob.nathanks.common.log.repository.LogStore
import com.jinyeob.nathanks.core.common.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class LogRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val preferencesRepository: PreferencesRepository
) : LogRepository {

    override fun d(msg: String, tag: String) {
        msg(msg).run {
            Log.d(tag, this)
        }

        showDebugModeToast(msg = msg, tag = tag)
    }

    override fun e(msg: String, exception: Exception?, tag: String) {
        msg(msg).run {
            LogStore.saveLog(this, context, LogLevel.ERROR)
            Log.e(tag, this, exception)

            FirebaseCrashlytics.getInstance().recordException(exception ?: Exception(this))
        }

        showDebugModeErrorToast(msg = msg, tag = tag)
    }

    override fun et(msg: String, exception: Throwable?, tag: String) {
        msg(msg).run {
            LogStore.saveLog(this, context, LogLevel.ERROR)
            Log.e(tag, this, exception)

            FirebaseCrashlytics.getInstance().recordException(exception ?: Exception(this))
        }

        showDebugModeErrorToast(msg = msg, tag = tag)
    }

    override fun lc(activity: Any, method: String) {
        Log.d(TAG_LIFECYCLE, "$method: ${activity.getName()}")
    }

    override fun getLogFiles(): List<File> {
        return context.getLogFiles()
    }

    private fun Any.getName() = javaClass.simpleName

    private fun showDebugModeErrorToast(msg: String, tag: String) {
        try {
            if (!BuildConfig.DEBUG_MODE) {
                return
            }

            if (!preferencesRepository.getPreference("dev_toast", false)) return
            if (tag == TAG_BILLING) return
            if (preferencesRepository.getPreference("dev_error_toast", false)) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
        } catch (_: Exception) { }
    }

    private fun showDebugModeToast(msg: String, tag: String) {
        try {
            if (!BuildConfig.DEBUG_MODE) { return }
            if (!preferencesRepository.getPreference("dev_toast", false)) return
            if (tag == TAG_ANALYTICS_EVENT) {
                if (preferencesRepository.getPreference("dev_event_toast", false)) {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            } else if (preferencesRepository.getPreference("dev_debug_toast", false) && tag != TAG_BILLING) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }
        } catch (_: Exception) { }
    }

    private fun msg(msg: String): String {
        return try {
            val level = 5
            val trace = Thread.currentThread().stackTrace[level]
            val fileName = trace.fileName
            /*
            val classPath = trace.className
            val className = try { classPath.substring(classPath.lastIndexOf(".") + 1) } catch (e: Exception) { classPath }
            */
            val methodName = trace.methodName
            val lineNumber = trace.lineNumber
            "::($fileName:$lineNumber).$methodName:: $msg"
        } catch (e: Exception) {
            msg
        }
    }

    companion object {
        const val TAG = "df_log"
        const val TAG_LIFECYCLE = "df_lc"
        const val TAG_WIDGET_LIFECYCLE = "df_wg"
        const val TAG_ANALYTICS_EVENT = "df_event"
        const val TAG_NOTIFICATION = "df_nf"
        const val TAG_BILLING = "df_bl"
    }
}

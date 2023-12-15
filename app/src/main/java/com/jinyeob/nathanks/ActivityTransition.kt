package com.jinyeob.nathanks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import java.io.Serializable

object ActivityTransition {
    fun Activity?.setEnterTransition() {
        this?.overridePendingTransition(R.anim.page_enter_transition_start, R.anim.page_enter_transition_end)
    }

    fun Activity?.setExitTransition() {
        this?.overridePendingTransition(R.anim.page_exit_transition_start, R.anim.page_exit_transition_end)
    }

    /**
     * Activity
     */
    fun <T> Activity?.startTransitionActivity(toActivity: Class<T>) {
        this?.startActivity(Intent(this, toActivity))
        setEnterTransition()
    }

    fun <T, G> Activity?.startTransitionActivity(toActivity: Class<T>, map: Map<String, G>) {
        val intent = Intent(this, toActivity)
        map.forEach {
            intent.putValue(it)
        }
        this?.startActivity(intent)
        setEnterTransition()
    }

    fun <T> Activity?.startTransitionActivity(toActivity: Class<T>, activityForResultLauncher: ActivityResultLauncher<Intent>) {
        activityForResultLauncher.launch(Intent(this, toActivity))
        setEnterTransition()
    }

    fun <T, G> Activity?.startTransitionActivity(toActivity: Class<T>, map: Map<String, G> = emptyMap(), activityForResultLauncher: ActivityResultLauncher<Intent>) {
        val intent = Intent(this, toActivity)
        map.forEach {
            intent.putValue(it)
        }
        activityForResultLauncher.launch(intent)
        setEnterTransition()
    }

    /**
     * Fragment
     */

    fun <T> Fragment.startTransitionActivity(toActivity: Class<T>) {
        startActivity(Intent(activity, toActivity))
        activity?.setEnterTransition()
    }

    fun <T, G> Fragment.startTransitionActivity(toActivity: Class<T>, map: Map<String, G> = emptyMap()) {
        val intent = Intent(activity, toActivity)
        map.forEach {
            intent.putValue(it)
        }
        startActivity(intent)
        activity?.setEnterTransition()
    }

    fun <T> Fragment.startTransitionActivity(toActivity: Class<T>, activityForResultLauncher: ActivityResultLauncher<Intent>) {
        activityForResultLauncher.launch(Intent(activity, toActivity))
        activity?.setEnterTransition()
    }

    fun <T, G> Fragment.startTransitionActivity(toActivity: Class<T>, map: Map<String, G> = emptyMap(), activityForResultLauncher: ActivityResultLauncher<Intent>) {
        val intent = Intent(activity, toActivity)
        map.forEach {
            intent.putValue(it)
        }
        activityForResultLauncher.launch(intent)
        activity?.setEnterTransition()
    }

    private fun <G> Intent.putValue(
        it: Map.Entry<String, G>
    ) {
        when (it.value) {
            is String -> putExtra(it.key, it.value as String)
            is Boolean -> putExtra(it.key, it.value as Boolean)
            is BooleanArray -> putExtra(it.key, it.value as BooleanArray)
            is Byte -> putExtra(it.key, it.value as Byte)
            is ByteArray -> putExtra(it.key, it.value as ByteArray)
            is Char -> putExtra(it.key, it.value as Char)
            is CharArray -> putExtra(it.key, it.value as CharArray)
            is CharSequence -> putExtra(it.key, it.value as CharSequence)
            is Double -> putExtra(it.key, it.value as Double)
            is DoubleArray -> putExtra(it.key, it.value as DoubleArray)
            is Float -> putExtra(it.key, it.value as Float)
            is FloatArray -> putExtra(it.key, it.value as FloatArray)
            is Int -> putExtra(it.key, it.value as Int)
            is IntArray -> putExtra(it.key, it.value as IntArray)
            is Long -> putExtra(it.key, it.value as Long)
            is LongArray -> putExtra(it.key, it.value as LongArray)
            is Short -> putExtra(it.key, it.value as Short)
            is ShortArray -> putExtra(it.key, it.value as ShortArray)
            is Bundle -> putExtra(it.key, it.value as Bundle)
            is Parcelable -> putExtra(it.key, it.value as Parcelable)
            is Serializable -> putExtra(it.key, it.value as Serializable)
        }
    }
}

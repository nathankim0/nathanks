package com.jinyeob.nathanks.preferences.repository

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PreferencesRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val editor: Editor
) : PreferencesRepository {

    override fun <T> getPreference(key: String, defaultValue: T): T {
        when (defaultValue) {
            is Boolean -> {
                val ret = sharedPreferences.getBoolean(key, (defaultValue as Boolean))
                return ret as T
            }
            is Collection<*> -> {
                val result = sharedPreferences.getStringSet(key, HashSet())
                return result as T? ?: return defaultValue
            }
            is String -> {
                val ret = sharedPreferences.getString(key, defaultValue as String)
                return ret as T? ?: return defaultValue
            }
            is Float -> {
                val result = sharedPreferences.getFloat(key, (defaultValue as Float))
                return result as T
            }
            is Long -> {
                val result = sharedPreferences.getLong(key, (defaultValue as Long))
                return result as T
            }
            is Int -> {
                val result = sharedPreferences.getInt(key, (defaultValue as Int))
                return result as T
            }
            else -> return defaultValue
        }
    }

    override fun <T> setPreference(key: String?, value: T) {
        when (value) {
            is Boolean -> {
                editor.putBoolean(key, (value as Boolean))
            }
            is Set<*> -> {
                editor.putStringSet(key, value as Set<String?>)
            }
            is String -> {
                editor.putString(key, value as String)
            }
            is Float -> {
                editor.putFloat(key, (value as Float))
            }
            is Long -> {
                editor.putLong(key, (value as Long))
            }
            is Int -> {
                editor.putInt(key, (value as Int))
            }
        }
        editor.apply()
    }

    override fun clearAll() {
        editor.clear()
        editor.apply()
    }
}

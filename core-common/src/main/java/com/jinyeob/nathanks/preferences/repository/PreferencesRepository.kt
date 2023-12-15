package com.jinyeob.nathanks.preferences.repository

interface PreferencesRepository {
    fun <T> getPreference(key: String, defaultValue: T): T
    fun <T> setPreference(key: String?, value: T)
    fun clearAll()
}

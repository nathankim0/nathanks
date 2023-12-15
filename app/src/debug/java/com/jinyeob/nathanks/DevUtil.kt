package com.jinyeob.nathanks

import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

object DevUtil {
    fun PreferenceFragmentCompat.setPreferenceText(key: String, summaryText: String) {
        findPreference<Preference>(key)?.summary = summaryText
    }
}

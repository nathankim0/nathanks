package com.jinyeob.nathanks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.jinyeob.nathanks.DevUtil.setPreferenceText
import com.jinyeob.nathanks.DeviceInfoUtil.deviceOs
import com.jinyeob.nathanks.DeviceInfoUtil.deviceSdk
import com.jinyeob.nathanks.MainApplication.Companion.getVersion
import com.jinyeob.nathanks.databinding.ActivityDevDeviceInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DevDeviceInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDevDeviceInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDevDeviceInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, DevDeviceInfoFragment())
                .commit()
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    class DevDeviceInfoFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            preferenceManager.sharedPreferencesName = "pref"
            setPreferencesFromResource(R.xml.dev_device_info_preferences, rootKey)

            setPreferenceText("dev_device_os", deviceOs)
            setPreferenceText("dev_device_sdk", deviceSdk.toString())
            setPreferenceText("dev_device_manufacturer", DeviceInfoUtil.manufacturer)
            setPreferenceText("dev_device_brand", DeviceInfoUtil.deviceBrand)
            setPreferenceText("dev_device_model", DeviceInfoUtil.deviceModel)
            setPreferenceText("dev_app_version", getVersion(requireContext()))
        }
    }
}

package com.jinyeob.nathanks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jinyeob.nathanks.MainApplication.Companion.clearAllData
import com.jinyeob.nathanks.common.onThrottleClick
import com.jinyeob.nathanks.databinding.ActivityDevRemoveAllBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DevRemoveAllActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDevRemoveAllBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDevRemoveAllBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.removeAllButton.onThrottleClick {
            BasicDialog.showAlertDialog(
                context = this@DevRemoveAllActivity,
                title = "주의!",
                message = "앱의 데이터를 전부 삭제합니다. 계속하시겠습니까?",
                positiveButton = "삭제",
                negativeButton = "취소",
                positiveListener = { clearAllData() }
            )
        }
    }
}

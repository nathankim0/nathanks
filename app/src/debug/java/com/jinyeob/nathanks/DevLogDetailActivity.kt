package com.jinyeob.nathanks

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import androidx.appcompat.app.AppCompatActivity
import com.jinyeob.nathanks.databinding.ActivityDevLogDetailBinding

class DevLogDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDevLogDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDevLogDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = intent.getStringExtra("log_name")
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.textView.text = intent.getStringExtra("log")
        val spannable = binding.textView.text as Spannable
        val start = findAllErrorMessageStartLength(binding.textView.text.toString())
        val last = findErrorMessageLastLength(binding.textView.text.toString())
        for (i in start.indices) {
            spannable.setSpan(
                android.text.style.ForegroundColorSpan(Color.RED),
                start[i],
                last[i] + 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        binding.scrollToBottomButton.setOnClickListener {
            binding.scrollView.fullScroll(android.view.View.FOCUS_DOWN)
        }
    }
    private fun findAllErrorMessageStartLength(text: String): List<Int> {
        val regex = Regex("error|exception|fail")
        val matchResult = regex.findAll(text)
        return matchResult.map { it.range.first }.toList()
    }

    private fun findErrorMessageLastLength(text: String): List<Int> {
        val regex = Regex("error|exception|fail")
        val matchResult = regex.findAll(text)
        return matchResult.map { it.range.last }.toList()
    }
}

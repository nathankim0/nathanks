package com.jinyeob.nathanks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jinyeob.nathanks.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.textView.text = "(changed text) Hello my name is Nathan Kim."

        setContentView(binding.root)
    }
}

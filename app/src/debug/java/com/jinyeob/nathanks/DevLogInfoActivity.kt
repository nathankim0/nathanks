package com.jinyeob.nathanks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jinyeob.nathanks.ActivityTransition.startTransitionActivity
import com.jinyeob.nathanks.BasicDialog.showAlertDialog
import com.jinyeob.nathanks.LogStore.getLogFiles
import com.jinyeob.nathanks.databinding.ActivityDevLogInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class DevLogInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDevLogInfoBinding

    @Inject
    lateinit var logRecyclerViewAdapter: LogRecyclerViewAdapter

    private lateinit var logFiles: List<File>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDevLogInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        logFiles = getLogFiles()

        binding.recyclerView.run {
            adapter = logRecyclerViewAdapter.apply { setOnItemListener(onItemListener()) }
        }

        logRecyclerViewAdapter.submitList(logFiles)
    }

    private fun onItemListener() = object : LogRecyclerViewAdapter.OnItemListener {
        override fun onItemClick(item: File) {
            startTransitionActivity(
                DevLogDetailActivity::class.java,
                mapOf(
                    "log" to item.readText(),
                    "log_name" to item.name
                )
            )
        }

        override fun onItemLongClick(item: File) {
            showAlertDialog(
                context = this@DevLogInfoActivity,
                title = R.string.log_file_delete_title,
                message = R.string.log_file_delete_message,
                positiveButton = R.string.log_file_delete_positive_button,
                negativeButton = R.string.log_file_delete_negative_button,
                positiveListener = {
                    item.delete()
                    logRecyclerViewAdapter.submitList(getLogFiles())
                }
            )
        }
    }
}

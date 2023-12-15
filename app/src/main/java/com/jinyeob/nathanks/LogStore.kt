package com.jinyeob.nathanks

import android.content.Context
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object LogStore {

    private const val LOG_FILE_DATE_FORMAT = "yyyy_MM_dd"
    private const val LOG_FILE_DETAIL_DATE_FORMAT = "HH:mm:ss"

    private val localDateFileFormat: DateTimeFormatter = DateTimeFormatter.ofPattern(LOG_FILE_DATE_FORMAT)
    private val localDateFileDetailFormat: DateTimeFormatter = DateTimeFormatter.ofPattern(LOG_FILE_DETAIL_DATE_FORMAT, Locale.ENGLISH)

    private val fileName = "log_${LocalDateTime.now().format(localDateFileFormat)}.file"

    fun saveLog(text: String, context: Context, logLevel: LogLevel) {
        val now = LocalDateTime.now()

        File(context.getLogFilePath(), fileName).run {
            createLogFile()
            saveLogText(now, logLevel, text)
        }
    }

    private fun File.createLogFile() {
        if (exists()) return

        try {
            createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun File.saveLogText(now: LocalDateTime, logLevel: LogLevel, text: String) =
        try {
            BufferedWriter(FileWriter(this, true)).run {
                append("[${now.format(localDateFileDetailFormat)}][${logLevel.txt}]$text")
                newLine()
                newLine()
                close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    fun Context.getLogFiles(): List<File> {
        val logFiles = mutableListOf<File>()
        val logDir = File(getLogFilePath())
        if (logDir.exists()) {
            logDir.listFiles()?.let {
                for (file in it) {
                    if (file.isFile) {
                        logFiles.add(file)
                    }
                }
            }
        }
        return logFiles
    }

    private fun Context.getLogFilePath() = cacheDir.absolutePath
}

enum class LogLevel(val txt: String) {
    ERROR("error"), DEBUG("debug")
}

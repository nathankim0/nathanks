package com.jinyeob.nathanks.log.repository

import com.jinyeob.nathanks.log.repository.LogRepositoryImpl
import java.io.File

interface LogRepository {
    fun d(msg: String, tag: String = LogRepositoryImpl.TAG)

    fun e(msg: String, exception: Exception? = null, tag: String = LogRepositoryImpl.TAG)

    fun et(msg: String, exception: Throwable? = null, tag: String = LogRepositoryImpl.TAG)

    fun lc(activity: Any, method: String)

    fun getLogFiles(): List<File>
}

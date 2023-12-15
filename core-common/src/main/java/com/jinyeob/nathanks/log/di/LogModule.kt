package com.jinyeob.nathanks.log.di

import com.jinyeob.nathanks.log.repository.LogRepository
import com.jinyeob.nathanks.log.repository.LogRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface LogModule {

    @Binds
    @Singleton
    fun providesLogRepository(logRepositoryImpl: LogRepositoryImpl): LogRepository
}

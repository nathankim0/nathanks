package com.jinyeob.nathanks.preferences.di

import android.content.Context
import android.content.SharedPreferences
import com.jinyeob.nathanks.preferences.repository.PreferencesRepository
import com.jinyeob.nathanks.preferences.repository.PreferencesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface PreferencesModule {

    @Binds
    @Singleton
    fun bindsPreferencesRepository(preferencesRepositoryImpl: PreferencesRepositoryImpl): PreferencesRepository

    companion object {
        @Provides
        @Singleton
        fun providesSharedPreferencesEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor = sharedPreferences.edit()

        @Provides
        @Singleton
        fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    }
}

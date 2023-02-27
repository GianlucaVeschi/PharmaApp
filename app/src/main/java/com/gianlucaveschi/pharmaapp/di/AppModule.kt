package com.gianlucaveschi.pharmaapp.di

import android.content.Context
import android.content.SharedPreferences
import com.gianlucaveschi.pharmaapp.data.PreferencesRepositoryImpl
import com.gianlucaveschi.pharmaapp.domain.repo.PreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("preferences_name", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providePreferencesRepository(
        sharedPreferences: SharedPreferences
    ): PreferencesRepository = PreferencesRepositoryImpl(
        sharedPreferences
    )

}
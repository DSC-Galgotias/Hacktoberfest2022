package com.github.danishjamal104.notes.di

import com.github.danishjamal104.notes.data.local.CacheDataSource
import com.github.danishjamal104.notes.data.repository.auth.AuthRepository
import com.github.danishjamal104.notes.data.repository.auth.AuthRepositoryImpl
import com.github.danishjamal104.notes.data.repository.note.NotesRepository
import com.github.danishjamal104.notes.data.repository.note.NotesRepositoryImpl
import com.github.danishjamal104.notes.util.sharedpreference.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(cacheDataSource: CacheDataSource,
                              userPreferences: UserPreferences): AuthRepository {
        return AuthRepositoryImpl(cacheDataSource, userPreferences)
    }

    @Singleton
    @Provides
    fun provideNotesRepository(cacheDataSource: CacheDataSource,
                               userPreferences: UserPreferences): NotesRepository {
        return NotesRepositoryImpl(cacheDataSource, userPreferences)
    }
}
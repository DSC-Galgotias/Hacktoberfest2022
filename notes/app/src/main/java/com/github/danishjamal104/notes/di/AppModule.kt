package com.github.danishjamal104.notes.di

import android.content.Context
import com.github.danishjamal104.notes.ui.Notes
import com.github.danishjamal104.notes.ui.fragment.home.adapter.NotesAdapter
import com.github.danishjamal104.notes.util.sharedpreference.UserPreferences
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
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
    fun provideBaseApplication(@ApplicationContext context: Context): Notes {
        return context as Notes
    }

    @Singleton
    @Provides
    fun providesUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }

    @Singleton
    @Provides
    fun providesGoogleSigningOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()
    }

    @Singleton
    @Provides
    fun providesGoogleSignInClient(@ApplicationContext context: Context,
                                   gso: GoogleSignInOptions): GoogleSignInClient {
        return GoogleSignIn.getClient(context, gso)
    }

    @Singleton
    @Provides
    fun provideGoogleSignInAccount(@ApplicationContext context: Context): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }

    @Singleton
    @Provides
    fun provideNoteAdapter(@ApplicationContext context: Context): NotesAdapter {
        return NotesAdapter(context)
    }

}
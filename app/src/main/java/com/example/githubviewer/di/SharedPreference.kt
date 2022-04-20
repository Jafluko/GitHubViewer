package com.example.githubviewer.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreference {

    @Provides
    @Singleton
    fun getSharePreference(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences("App", Context.MODE_PRIVATE)
}
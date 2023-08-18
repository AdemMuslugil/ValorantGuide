package com.ademmuslugil.valorantguide.di

import android.content.Context
import com.ademmuslugil.valorantguide.util.PrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Singleton
    @Provides
    fun providePrefManager(@ApplicationContext context: Context) = PrefManager(context = context)
}
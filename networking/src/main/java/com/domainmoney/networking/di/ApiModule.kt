package com.domainmoney.networking.di

import com.domainmoney.networking.services.AccountApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Provides
    @Singleton
    fun provideAccountApiService(
        retrofit: Retrofit
    ): AccountApiService = retrofit.create(AccountApiService::class.java)
}

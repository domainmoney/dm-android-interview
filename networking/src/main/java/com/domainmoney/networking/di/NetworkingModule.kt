package com.domainmoney.networking.di

import com.domainmoney.common.annotations.ApiUrl
import com.domainmoney.common.annotations.DebugBuild
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.LoggingEventListener
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkingModule {
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(
        @DebugBuild isDebug: Boolean
    ): HttpLoggingInterceptor? =
        if (isDebug) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        else null

    @Singleton
    @Provides
    fun provideHttpEventListener(
        @DebugBuild isDebug: Boolean
    ): LoggingEventListener.Factory? = if (isDebug) LoggingEventListener.Factory() else null

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor?,
        loggingEventListener: LoggingEventListener.Factory?
    ): OkHttpClient = OkHttpClient.Builder()
        .apply {
            if (httpLoggingInterceptor != null) {
                addInterceptor(httpLoggingInterceptor)
            }
            if (loggingEventListener != null) {
                eventListenerFactory(loggingEventListener)
            }
        }
        .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .build()

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(
        moshi: Moshi
    ): MoshiConverterFactory = MoshiConverterFactory.create(moshi)

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
        @ApiUrl apiUrl: String
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .build()
}

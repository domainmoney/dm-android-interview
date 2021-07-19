package com.domainmoney.interview.di

import android.app.Application
import com.domainmoney.common.annotations.ApiUrl
import com.domainmoney.common.annotations.AppVersion
import com.domainmoney.common.annotations.ApplicationId
import com.domainmoney.common.annotations.DebugBuild
import com.domainmoney.interview.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @ApplicationId
    fun provideApplicationId(application: Application): String = application.packageName

    @Provides
    @DebugBuild
    fun provideIsDebugBuild(): Boolean = BuildConfig.DEBUG

    @Provides
    @AppVersion
    fun provideApplicationVersion(
        @ApplicationId applicationId: String,
        @DebugBuild isDebug: Boolean,
        application: Application
    ): String {
        val packageInfo = application.packageManager.getPackageInfo(applicationId, 0)
        return if (isDebug) {
            "${packageInfo.versionName}-debug (${packageInfo.versionCode})"
        } else {
            packageInfo.versionName
        }
    }

    @Provides
    @ApiUrl
    fun provideApiUrl(): String = "https://mock.server/api/"
}

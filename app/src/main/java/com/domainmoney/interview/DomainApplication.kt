package com.domainmoney.interview

import android.app.Application
import com.domainmoney.interview.init.AppInitializer
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DomainApplication : Application() {
    @Inject lateinit var initializers: Set<@JvmSuppressWildcards AppInitializer>

    override fun onCreate() {
        super.onCreate()
        initializers.forEach {
            it.init(this)
        }
    }
}

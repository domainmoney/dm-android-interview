package com.domainmoney.interview.init

import android.app.Application
import com.domainmoney.common.annotations.DebugBuild
import timber.log.Timber
import javax.inject.Inject

class TimberInitializer @Inject constructor(
    @DebugBuild private val isDebug: Boolean
) : AppInitializer {
    override fun init(application: Application) {
        if (isDebug) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

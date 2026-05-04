package com.fintrack

import android.app.Application
import com.fintrack.data.db.seed.DevSeedRunner
import com.fintrack.security.InactivityTracker
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class FintrackApp : Application() {

    @Inject lateinit var inactivityTracker: InactivityTracker
    @Inject lateinit var devSeedRunner: DevSeedRunner

    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        // Locks the session whenever the app goes background longer than the
        // configured inactivity timeout. The boolean state is consumed by
        // AppViewModel and drives the navigation graph back to LockRoute.
        inactivityTracker.attach()

        if (BuildConfig.DEBUG) {
            appScope.launch {
                runCatching { devSeedRunner.runIfNeeded() }
            }
        }
    }
}

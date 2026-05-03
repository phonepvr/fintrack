package com.fintrack

import android.app.Application
import com.fintrack.security.InactivityTracker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class FintrackApp : Application() {

    @Inject lateinit var inactivityTracker: InactivityTracker

    override fun onCreate() {
        super.onCreate()
        // Locks the session whenever the app goes background longer than the
        // configured inactivity timeout. The boolean state is consumed by
        // AppViewModel and drives the navigation graph back to LockRoute.
        inactivityTracker.attach()
    }
}

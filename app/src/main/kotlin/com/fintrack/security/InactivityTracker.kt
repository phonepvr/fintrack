package com.fintrack.security

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * Tracks app foreground/background transitions to drive the biometric re-lock
 * decision.
 *
 * Lifecycle:
 *  - Hooked into [ProcessLifecycleOwner] from [com.fintrack.FintrackApp.onCreate].
 *  - On `onStop` we record the timestamp.
 *  - On `onStart` we expose [shouldRelock] = (now - lastBackground >= timeout).
 *
 * The actual lock UI is gated by the [com.fintrack.MainActivity], not by this
 * observer — this class only computes the boolean.
 *
 * NOTE: switching profiles within the inactivity window does NOT count as a
 * lock event. We deliberately do not reset lastBackground on user switch.
 */
@Singleton
class InactivityTracker @Inject constructor() : DefaultLifecycleObserver {

    private val _isUnlocked = MutableStateFlow(false)
    val isUnlocked: StateFlow<Boolean> = _isUnlocked.asStateFlow()

    private var lastBackgroundElapsedRealtime: Long = 0L
    private var inactivityTimeout: Duration = DEFAULT_TIMEOUT

    fun attach() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    fun setInactivityTimeout(seconds: Int) {
        inactivityTimeout = seconds.coerceAtLeast(MIN_TIMEOUT_SECONDS).seconds
    }

    /** Mark the user as authenticated for the current foreground session. */
    fun markUnlocked() {
        _isUnlocked.value = true
    }

    /** Force a lock — used on cold start and when the inactivity threshold trips. */
    fun forceLock() {
        _isUnlocked.value = false
    }

    override fun onStart(owner: LifecycleOwner) {
        if (!_isUnlocked.value) return
        if (lastBackgroundElapsedRealtime == 0L) return
        val elapsed = (nowElapsedRealtime() - lastBackgroundElapsedRealtime).coerceAtLeast(0L)
        if (elapsed >= inactivityTimeout.inWholeMilliseconds) {
            _isUnlocked.value = false
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        lastBackgroundElapsedRealtime = nowElapsedRealtime()
    }

    // SystemClock.elapsedRealtime is monotonic and unaffected by wall-clock changes.
    private fun nowElapsedRealtime(): Long = android.os.SystemClock.elapsedRealtime()

    companion object {
        private const val MIN_TIMEOUT_SECONDS = 5
        private val DEFAULT_TIMEOUT = 60.seconds
    }
}

package com.kaixueio.lifecycle

interface LifecycleObserver {
    fun onStateChanged(event: Lifecycle.Event)
}

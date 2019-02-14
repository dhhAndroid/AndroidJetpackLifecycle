package com.kaixueio.lifecycle

import java.util.concurrent.CopyOnWriteArrayList

/**
 * Created by dhh on 2019/1/30.
 *
 * @author dhh
 */
internal class LifecycleRegistry : Lifecycle {
    private val observerList by lazy { CopyOnWriteArrayList<LifecycleObserver>() }

    override fun addObserver(observer: LifecycleObserver) {
        observerList.add(observer)
    }

    override fun removeObserver(observer: LifecycleObserver) {
        observerList.remove(observer)
    }

    fun handleLifecycleEvent(event: Lifecycle.Event) {
        observerList.forEach { it.onStateChanged(event) }
        if (event == Lifecycle.Event.ON_DESTROY) {
            observerList.clear()
        }
    }
}
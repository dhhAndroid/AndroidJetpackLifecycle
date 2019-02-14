package com.kaixueio.lifecycle

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.support.annotation.CallSuper

/**
 * Created by dhh on 2019/1/30.
 *
 * @author dhh
 */
@SuppressLint("Registered")
open class SupportActivity : Activity(), LifecycleOwner {
    protected val TAG = javaClass.simpleName
    private val lifecycleRegistry = LifecycleRegistry()

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ReportFragment.injectIfNeededIn(this)
    }

}
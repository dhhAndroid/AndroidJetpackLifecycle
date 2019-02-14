/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kaixueio.lifecycle

import android.app.Activity
import android.app.Fragment
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle

/**
 * 用来发送 [[Activity]]/[[android.support.v4.app.Fragment]]
 *
 */
class ReportFragment : Fragment() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dispatch(Lifecycle.Event.ON_CREATE)
    }

    override fun onStart() {
        super.onStart()
        dispatch(Lifecycle.Event.ON_START)
    }

    override fun onResume() {
        super.onResume()
        dispatch(Lifecycle.Event.ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        dispatch(Lifecycle.Event.ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        dispatch(Lifecycle.Event.ON_STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        dispatch(Lifecycle.Event.ON_DESTROY)
    }

    private fun dispatch(event: Lifecycle.Event) {
        val activity = activity

        if (activity is LifecycleOwner) {
            val lifecycle = (activity as LifecycleOwner).lifecycle
            if (lifecycle is LifecycleRegistry) {
                lifecycle.handleLifecycleEvent(event)
            }
        }
    }

    companion object {

        private val REPORT_FRAGMENT_TAG = "android.arch.lifecycle" + ".LifecycleDispatcher.report_fragment_tag"

        fun injectIfNeededIn(activity: Activity) {
            val manager = activity.fragmentManager
            if (manager.findFragmentByTag(REPORT_FRAGMENT_TAG) == null) {
                manager.beginTransaction().add(ReportFragment(), REPORT_FRAGMENT_TAG).commit()
                manager.executePendingTransactions()
            }
        }
    }
}

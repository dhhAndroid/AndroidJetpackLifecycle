package com.kaixueio.lifecycle

import android.app.Activity
import android.app.Fragment
import android.os.Bundle

/**
 * 用来发送 [android.app.Activity]/[android.support.v4.app.Fragment] 生命周期的空 Fragment
 *
 */
class ReportFragment : Fragment() {

    private val lifecycle by lazy { (activity as? LifecycleOwner)?.let { it.getLifecycle() as? LifecycleRegistry } }
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
        lifecycle?.handleLifecycleEvent(event)
    }

    companion object {

        private val REPORT_FRAGMENT_TAG = "com.kaixueio.lifecycle.LifecycleDispatcher.report_fragment_tag"

        fun injectIfNeededIn(activity: Activity) {
            val manager = activity.fragmentManager
            if (manager.findFragmentByTag(REPORT_FRAGMENT_TAG) == null) {
                manager.beginTransaction().add(ReportFragment(), REPORT_FRAGMENT_TAG).commit()
                manager.executePendingTransactions()
            }
        }
    }
}

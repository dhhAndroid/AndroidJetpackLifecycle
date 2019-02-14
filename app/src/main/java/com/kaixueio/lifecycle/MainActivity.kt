package com.kaixueio.lifecycle

import android.os.Bundle
import android.util.Log

class MainActivity : SupportActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getLifecycle()
            .addObserver(object : LifecycleObserver {
                override fun onStateChanged(event: Lifecycle.Event) {
                    Log.d(TAG, event.name)
                }
            })
    }
}

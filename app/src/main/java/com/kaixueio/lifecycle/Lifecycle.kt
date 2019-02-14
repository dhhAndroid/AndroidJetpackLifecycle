package com.kaixueio.lifecycle


abstract class Lifecycle {

    abstract fun addObserver(observer: LifecycleObserver)

    abstract fun removeObserver(observer: LifecycleObserver)

    enum class Event {
        /**
         * Constant for onCreate event of the [LifecycleOwner].
         */
        ON_CREATE,
        /**
         * Constant for onStart event of the [LifecycleOwner].
         */
        ON_START,
        /**
         * Constant for onResume event of the [LifecycleOwner].
         */
        ON_RESUME,
        /**
         * Constant for onPause event of the [LifecycleOwner].
         */
        ON_PAUSE,
        /**
         * Constant for onStop event of the [LifecycleOwner].
         */
        ON_STOP,
        /**
         * Constant for onDestroy event of the [LifecycleOwner].
         */
        ON_DESTROY,
        /**
         * An [Event] constant that can be used to match all events.
         */
        ON_ANY
    }

}

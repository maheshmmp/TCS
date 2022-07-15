package com.example.mynewsapplication.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 *@HiltAndroidApp: we need to apply this annotation to our Application class,
 *  It will trigger the Hilt code generation and in the process will create our App Component.
 */

@HiltAndroidApp
class MyNewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @get:Synchronized
        var instance: MyNewsApp? = null
        private val MAX_HEAP_SIZE = Runtime.getRuntime().maxMemory().toInt()
        fun applicationContext(): MyNewsApp {
            return instance as MyNewsApp
        }
    }

}

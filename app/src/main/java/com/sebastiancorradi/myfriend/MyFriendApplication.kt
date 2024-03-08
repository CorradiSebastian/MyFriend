package com.sebastiancorradi.myfriend

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyFriendApplication: Application() {
    companion object {
        private var instance: MyFriendApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        // Setup singleton instance
        instance = this
    }
}
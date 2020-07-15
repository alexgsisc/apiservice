package com.example.apiservice.application

import android.content.Context
import androidx.multidex.MultiDexApplication

class BadApplication : MultiDexApplication() {

    private var context: Context? = null

    fun getAppContext(): Context {
        return context!!
    }

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
    }

    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(context)
    }
}
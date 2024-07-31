package com.daywei.mediavideotest

import android.app.Application
import com.daywei.mediavideotest.manager.ResourceManager

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ResourceManager.INSTANCE.init(this)





    }
}
package com.daywei.mediavideotest.manager

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class ResourceManager private constructor() {

    companion object {
        private var version:Int = 0
        val INSTANCE: ResourceManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ResourceManager()
        }
    }

    private var mContext: Context? = null
    private var currentResources: Resources? = null

    fun init(cxt: Context) {
        this.mContext = cxt
        currentResources = cxt.resources
    }

    fun release() {
        mContext = null
        currentResources = null
    }

    suspend fun getBitmap(resId: Int): Bitmap? {
        currentResources ?: return null
        return kotlin.runCatching {
            BitmapFactory.decodeResource(
                currentResources,
                resId
            )
        }.getOrNull()
    }

    suspend fun getString(resId: Int): String? {
        currentResources ?: return null
        return kotlin.runCatching { currentResources?.getString(resId) }.getOrNull()
    }
}
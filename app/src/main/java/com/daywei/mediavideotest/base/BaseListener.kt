package com.daywei.mediavideotest.base

interface BaseListener {
    fun request(event: Event?)
    fun onEvent(event: Event?)
}
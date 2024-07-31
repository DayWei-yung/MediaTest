package com.daywei.mediavideotest.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

abstract class BaseActivity<T : ViewDataBinding, E : BaseViewModel> :
    AppCompatActivity(), BaseListener, Observer<Event?> {

    protected var mViewModel: E? = null
    protected var mBinding: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        mBinding = DataBindingUtil.inflate(layoutInflater, getResId(), null, false)

        mViewModel = getViewModel()
        setContentView(mBinding?.root)
        init()
    }

    override fun onStart() {
        super.onStart()
        mViewModel?.onUiCreate(this, this)
    }

    override fun onStop() {
        super.onStop()
        mViewModel?.onUiDestroy(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        release()
        mViewModel = null
    }

    abstract fun getViewModel(): E
    abstract fun getResId(): Int
    abstract fun init()
    open fun release() {
        mViewModel = null
        mBinding = null
    }

    override fun request(event: Event?) {
        mViewModel?.onRequest(event)
    }

    override fun onChanged(t: Event?) {
        onEvent(t)
    }

    protected fun post(run: () -> Unit) {
        runOnUiThread {
            run()
        }
    }
}
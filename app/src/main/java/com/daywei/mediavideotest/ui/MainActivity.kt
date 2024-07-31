package com.daywei.mediavideotest.ui

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModelProvider
import com.daywei.mediavideo.BR
import com.daywei.mediavideo.R
import com.daywei.mediavideo.databinding.LayoutMainBinding
import com.daywei.mediavideotest.base.BaseActivity
import com.daywei.mediavideotest.base.Event
import com.daywei.mediavideotest.base.BaseViewModel
import com.daywei.mediavideotest.consts.*
import com.daywei.mediavideotest.entity.NaviData
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<LayoutMainBinding, BaseViewModel>(), View.OnClickListener {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            mBinding?.naviAudio?.id -> {
                resetSelectState()
                mBinding?.naviAudio?.setSelect(true)
            }
            mBinding?.naviVideo?.id -> {
                resetSelectState()
                mBinding?.naviVideo?.setSelect(true)
            }
            mBinding?.naviPersonal?.id -> {
                resetSelectState()
                mBinding?.naviPersonal?.setSelect(true)
            }
        }
    }

    override fun getViewModel(): BaseViewModel {
        return ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(NaviModel::class.java)
    }

    override fun getResId(): Int {
        return R.layout.layout_main
    }

    override fun onEvent(event: Event?) {
        Log.e(APP_TAG, "$TAG:onEvent, event: $event")
        MainScope().launch {
            when (event?.code) {
                EVENT_NAVI_AUDIO_LOAD -> mBinding?.naviAudio?.onEvent(event)
                EVENT_NAVI_VIDEO_LOAD -> mBinding?.naviVideo?.onEvent(event)
                EVENT_NAVI_PERSONAL_LOAD -> mBinding?.naviPersonal?.onEvent(event)
            }
        }
    }

    override fun init() {
        mBinding?.setVariable(BR.navi, mViewModel)
        (mViewModel as NaviModel).whenOnChange(TYPE_AUDIO, this::onAudioChange)
        (mViewModel as NaviModel).whenOnChange(TYPE_VIDEO, this::onVideoChange)
        (mViewModel as NaviModel).whenOnChange(TYPE_PERSONAL, this::onPersonalChange)

        mBinding?.naviAudio?.setOnClickListener(this)
        mBinding?.naviVideo?.setOnClickListener(this)
        mBinding?.naviPersonal?.setOnClickListener(this)
        mBinding?.naviAudio?.setSelect(true)
    }

    override fun release() {
        mBinding = null

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            request(Event(REQUEST_CODE_UPDATE_NAVI, null))
        }
    }

    private fun onAudioChange(field: ObservableField<*>) {
        post {
            Log.d(APP_TAG, "$TAG:onAudioChange, field:$field")
            mBinding?.naviAudio?.onEvent(Event(TYPE_AUDIO, field.get() as NaviData))
        }
    }

    private fun onVideoChange(field: ObservableField<NaviData>) {
        post {
            Log.d(APP_TAG, "$TAG:onVideoChange, field:$field")
            mBinding?.naviVideo?.onEvent(Event(TYPE_VIDEO, field.get() as NaviData))
        }
    }

    private fun onPersonalChange(field: ObservableField<NaviData>) {
        post {
            Log.d(APP_TAG, "$TAG:onVideoChange, field:$field")
            mBinding?.naviPersonal?.onEvent(Event(TYPE_PERSONAL, field.get() as NaviData))
        }
    }

    private fun resetSelectState() {
        mBinding?.naviAudio?.setSelect(false)
        mBinding?.naviVideo?.setSelect(false)
        mBinding?.naviPersonal?.setSelect(false)
    }
}
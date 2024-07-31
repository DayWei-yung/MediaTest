package com.daywei.mediavideotest.utils

import androidx.fragment.app.Fragment
import com.daywei.mediavideotest.ui.audio.AudioFragment
import com.daywei.mediavideotest.ui.personal.PersonalFragment
import com.daywei.mediavideotest.ui.video.VideoFragment

class ViewPagerFragmentFactory {
    companion object {
        // 根据位置排列创建 Fragment
        fun create(position : Int) : Fragment {
            return when (position) {
                0 -> AudioFragment()
                1 -> VideoFragment()
                else -> PersonalFragment()
            }
        }

        // 获取 Fragment 数量
        fun getFragmentCounts() : Int {
            return 3
        }
    }
}
package com.daywei.mediavideotest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.daywei.mediavideo.R
import com.daywei.mediavideotest.utils.ViewPagerFragmentFactory

class ViewPageAdapter(context: FragmentActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView
        .ViewHolder(LayoutInflater.from(mContext)
            .inflate(R.layout.viewpager_fragment,parent, false)) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        mContext.supportFragmentManager
            .beginTransaction()
            .add(R.id.viewpager_fragment,
                ViewPagerFragmentFactory.create(position))
            .commit()
    }

    override fun getItemCount(): Int {
        return ViewPagerFragmentFactory.getFragmentCounts()
    }
}
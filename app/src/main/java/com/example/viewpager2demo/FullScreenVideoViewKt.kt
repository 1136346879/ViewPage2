package com.example.viewpager2demo

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.VideoView

/**
 * Created by FullScreenVideoView on 2020/6/19.
 * Author：wdx
 * Description： This is 。。。
 **/
class FullScreenVideoViewKt(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : VideoView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
    }
}
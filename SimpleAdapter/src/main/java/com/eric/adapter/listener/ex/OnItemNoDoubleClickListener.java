package com.eric.adapter.listener.ex;

import android.view.View;

import com.eric.adapter.listener.OnItemClickListener;

/**
 * Author: Eric
 * Date: Created in 2019/1/26 4:09 PM
 * Description： simple adapter 的扩展，防止item在一定时间内重复点击
 */
public abstract class OnItemNoDoubleClickListener implements OnItemClickListener {

    // 上一次点击成功触发的时间，初始化为0
    private long lastClickTime = 0;

    @Override
    public void onClick(View v, int position) {
        long timeNow = System.currentTimeMillis();
        if (timeNow - lastClickTime > timeSlot()) {
            lastClickTime = timeNow;
            onNoDoubleCLick(v, position);
        }
    }

    /**
     * 自定义时间间隔，单位毫秒
     */
    protected abstract long timeSlot();

    /**
     * 点击事件触发，并且满足时间间隔时的回调
     */
    protected abstract void onNoDoubleCLick(View v, int position);
}

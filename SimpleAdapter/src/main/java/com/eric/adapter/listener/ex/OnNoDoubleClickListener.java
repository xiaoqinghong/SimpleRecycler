package com.eric.adapter.listener.ex;

import android.view.View;

/**
 * Author: Eric
 * Date: Created in 2019/1/26 4:23 PM
 * Description： View.OnClickListener的扩展，防止View在一定时间内重复点击
 */
public abstract class OnNoDoubleClickListener implements View.OnClickListener {

    // 上一次点击成功触发的时间，初始化为0
    private long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long timeNow = System.currentTimeMillis();
        if (timeNow - lastClickTime > timeSlot()) {
            lastClickTime = timeNow;
            onNoDoubleCLick(v);
        }
    }

    /**
     * 自定义时间间隔，单位毫秒
     */
    protected abstract long timeSlot();

    /**
     * 点击事件触发，并且满足时间间隔时的回调
     */
    protected abstract void onNoDoubleCLick(View v);
}

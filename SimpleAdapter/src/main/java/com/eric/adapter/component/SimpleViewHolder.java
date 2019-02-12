package com.eric.adapter.component;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.eric.adapter.listener.OnItemClickListener;
import com.eric.adapter.listener.OnItemLongClickListener;
import com.eric.adapter.listener.OnSubViewClickListener;
import com.eric.adapter.listener.OnSubViewLongClickListener;

final public class SimpleViewHolder extends RecyclerView.ViewHolder {
    static final int KEY_IS_ITEM = -1001;
    private boolean isHaveHeader = false; // 是否有header，若有，则计算位置的时候，需要减1

    private SparseArray<View> subViews = new SparseArray<>();

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnSubViewClickListener onSubViewClickListener;
    private OnSubViewLongClickListener onSubViewLongClickListener;

    private InnerClickListener mInnerClickListener;
    private InnerLongClickListener mInnerLongClickListener;

    SimpleViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    /**
     * 设置是否含有header
     */
    public void setHaveHeader(boolean haveHeader) {
        isHaveHeader = haveHeader;
    }

    //============ 以下是绑定点击事件监听的回调接口 ============//
    /**
     * item的点击事件回调接口
     * @param itemClickListener listener
     */
    void bindItemClickListener(OnItemClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
        // itemView click
        if (onItemClickListener != null) {
            if (mInnerClickListener == null) {
                mInnerClickListener = new InnerClickListener();
            }
            itemView.setOnClickListener(mInnerClickListener);
        }
    }

    /**
     * item的长按事件回调接口
     * @param itemLongClickListener listener
     */
    void bindItemLongClickListener(OnItemLongClickListener itemLongClickListener){
        this.onItemLongClickListener = itemLongClickListener;
        // itemView long click
        if (onItemLongClickListener != null) {
            if (mInnerLongClickListener == null) {
                mInnerLongClickListener = new InnerLongClickListener();
            }
            itemView.setOnLongClickListener(mInnerLongClickListener);
        }
    }

    /**
     * subView的点击事件的回调接口
     * @param subViewClickListener listener
     */
    void bindSubViewClickListener(OnSubViewClickListener subViewClickListener) {
        if (this.onSubViewClickListener == null)
            this.onSubViewClickListener = subViewClickListener;
    }

    /**
     * subView的长按事件回调接口
     * @param subViewLongClickListener listener
     */
    void bindSubViewLongClickListener(OnSubViewLongClickListener subViewLongClickListener) {
        if (this.onSubViewLongClickListener == null)
            this.onSubViewLongClickListener = subViewLongClickListener;

    }

    //============ 以下是两个点击事件监听的内部类 ============//
    /**
     * 点击事件的内部监听，整个viewHolder中只有一个
     */
    private class InnerClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //Integer position = (Integer) v.getTag(KEY_TEM_POSITION);
            int position = isHaveHeader? getLayoutPosition() - 1 : getLayoutPosition();
            Boolean isItemView = (Boolean)v.getTag(KEY_IS_ITEM);
            if (isItemView != null) {
                // 分发item或是subView
                if (isItemView) {
                    if (onItemClickListener!= null)
                        onItemClickListener.onClick(v, position);
                } else {
                    if (onSubViewClickListener != null)
                        onSubViewClickListener.onClick(v, position);
                }
            }
        }
    }

    /**
     * 长按事件的内部监听，整个viewHolder中只有一个
     */
    private class InnerLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            int position = isHaveHeader? getLayoutPosition() - 1 : getLayoutPosition();
            Boolean isItemView = (Boolean)v.getTag(KEY_IS_ITEM);
            if (isItemView != null) {
                // 分发item或是subView
                if (isItemView) {
                    if (onItemLongClickListener!= null)
                        onItemLongClickListener.onLongClick(v, position);
                } else {
                    if (onItemLongClickListener != null)
                        onItemLongClickListener.onLongClick(v, position);
                }
                return true;
            }
            return false;
        }
    }

    //============ 以下是操作itemView或subView ============//
    /**
     * 给View添加click监听事件
     * @param id id
     * @return this
     */
    public SimpleViewHolder addClickListener(int id){
        // subView click
        if (onSubViewClickListener != null) {
            if (mInnerClickListener == null) {
                mInnerClickListener = new InnerClickListener();
            }
            View view = getView(id);
            if (view != null) {
                if (!view.isClickable()) view.setClickable(true);
                view.setTag(KEY_IS_ITEM, false);
                view.setOnClickListener(mInnerClickListener);
            }
        }
        return this;
    }

    /**
     * 给View添加long click监听事件
     * @param id id
     * @return this
     */
    public SimpleViewHolder addLongClickListener(int id){
        // subView click
        if (onSubViewLongClickListener != null) {
            if (mInnerLongClickListener == null) {
                mInnerLongClickListener = new InnerLongClickListener();
            }
            View view = getView(id);
            if (view != null) {
                view.setTag(KEY_IS_ITEM, false);
                view.setOnLongClickListener(mInnerLongClickListener);
            }
        }
        return this;
    }

    /**
     * 设置textView的文字
     * @param id view id
     * @param text content
     * @return this
     */
    public SimpleViewHolder setText(@IdRes int id, CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            TextView view = getView(id);
            if (view != null) view.setText(text);
        }
        return this;
    }

    /**
     * 设置textView的文字
     * @param id view id
     * @param resId content
     * @return this
     */
    public SimpleViewHolder setText(@IdRes int id, @StringRes int resId) {
        String str = itemView.getContext().getString(resId);
        if (!TextUtils.isEmpty(str)) {
            TextView view = getView(id);
            if (view != null) view.setText(str);
        }
        return this;
    }

    /**
     * 设置textView的颜色
     * @param id id
     * @param resId resId
     * @return this
     */
    @SuppressLint("ResourceType")
    public SimpleViewHolder setTextColor(@IdRes int id, @ColorRes int resId) {
        TextView view = getView(id);
        if (view != null) view.setTextColor(ContextCompat.getColor(itemView.getContext(), resId));
        return this;
    }

    /**
     * 设置view的背景res
     * @param id id
     * @param resId resourceId
     * @return this
     */
    public SimpleViewHolder setBackgroundResource(@IdRes int id, @DrawableRes int resId) {
        View view = getView(id);
        if (view != null) view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置view的背景颜色
     */
    public SimpleViewHolder setBackgroundColor(@IdRes int id, @ColorInt int color) {
        View view = getView(id);
        if (view != null) view.setBackgroundColor(color);
        return this;
    }

    /**
     * 设置view的背景颜色
     */
    public SimpleViewHolder setBackgroundColorRes(@IdRes int id, @ColorRes int resId) {
        View view = getView(id);
        if (view != null) view.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), resId));
        return this;
    }

    /**
     * 设置imageView
     */
    public SimpleViewHolder setImageResource(@IdRes int id, int resId) {
        ImageView view = getView(id);
        if (view != null) view.setImageResource(resId);
        return this;
    }

    /**
     * 为imageView 设置bitmap
     */
    public SimpleViewHolder setImageBitmap(@IdRes int id, Bitmap bitmap) {
        ImageView view = getView(id);
        if (view != null) view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置view的gone或visible
     * isVisible == true:View.VISIBLE
     * isVisible == false:View.GONE
     */
    public SimpleViewHolder setGone(@IdRes int id, boolean isVisible) {
        View view = getView(id);
        if (view != null) {
            view.setVisibility(isVisible ? View.VISIBLE:View.GONE);
        }
        return this;
    }

    /**
     * 设置view的invisible或visible
     * isVisible == true:View.VISIBLE
     * isVisible == false:View.INVISIBLE
     */
    public SimpleViewHolder setVisible(@IdRes int id, boolean isVisible) {
        View view = getView(id);
        if (view != null) {
            view.setVisibility(isVisible ? View.VISIBLE:View.INVISIBLE);
        }
        return this;
    }

    /**
     * findViewById
     */
    public <T extends View> T getView(@IdRes int id) {
        View t = subViews.get(id);
        if (t == null) {
            t = itemView.findViewById(id);
            subViews.put(id, t);
        }
        return (T) t;
    }
}

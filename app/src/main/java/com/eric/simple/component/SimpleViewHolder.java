package com.eric.simple.component;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

final public class SimpleViewHolder extends RecyclerView.ViewHolder {
    static final int KEY_TEM_POSITION = -1000;
    static final int KEY_IS_ITEM = -1001;

    private SimpleRecyclerAdapter.OnItemClickListener onItemClickListener;
    private SimpleRecyclerAdapter.OnItemLongClickListener onItemLongClickListener;
    private SimpleRecyclerAdapter.OnSubViewClickListener onSubViewClickListener;
    private SimpleRecyclerAdapter.OnSubViewLongClickListener onSubViewLongClickListener;

    private InnerClickListener mInnerClickListener;
    private InnerLongClickListener mInnerLongClickListener;

    SimpleViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    //============ 以下是绑定点击事件监听的回调接口 ============//
    /**
     * bind item click. only inner adapter can call this method.
     * @param itemClickListener listener
     */
    void bindItemClickListener(SimpleRecyclerAdapter.OnItemClickListener itemClickListener) {
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
     * bind item long click. only inner adapter can call this method.
     * @param itemLongClickListener listener
     */
    void bindItemLongClickListener(SimpleRecyclerAdapter.OnItemLongClickListener itemLongClickListener){
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
     * bind subView click. only inner adapter can call this method.
     * @param subViewClickListener listener
     */
    void bindSubViewClickListener(SimpleRecyclerAdapter.OnSubViewClickListener subViewClickListener) {
        if (this.onSubViewClickListener == null)
            this.onSubViewClickListener = subViewClickListener;
    }

    /**
     * bind subView long click. only inner adapter can call this method.
     * @param subViewLongClickListener listener
     */
    void bindSubViewLongClickListener(SimpleRecyclerAdapter.OnSubViewLongClickListener subViewLongClickListener) {
        this.onSubViewLongClickListener = subViewLongClickListener;

    }

    //============ 以下是两个点击事件监听的内部类 ============//
    /**
     * click 内部监听
     */
    private class InnerClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Integer position = (Integer) v.getTag(KEY_TEM_POSITION);
            Boolean isItemView = (Boolean)v.getTag(KEY_IS_ITEM);
            if (position != null && isItemView != null) {
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
     * longClick 内部监听
     */
    private class InnerLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            Integer position = (Integer) v.getTag(KEY_TEM_POSITION);
            Boolean isItemView = (Boolean)v.getTag(KEY_IS_ITEM);
            if (position != null && isItemView != null) {
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
            View subView = itemView.findViewById(id);
            subView.setTag(KEY_TEM_POSITION, itemView.getTag(KEY_TEM_POSITION));
            subView.setTag(KEY_IS_ITEM, false);
            subView.setOnClickListener(mInnerClickListener);
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
            View subView = itemView.findViewById(id);
            subView.setTag(KEY_TEM_POSITION, itemView.getTag(KEY_TEM_POSITION));
            subView.setTag(KEY_IS_ITEM, false);
            subView.setOnLongClickListener(mInnerLongClickListener);
        }
        return this;
    }

    /**
     * this method will call 'setText()' from TextView
     * @param id view id
     * @param text content
     * @return this
     */
    public SimpleViewHolder setText(int id, String text) {
        if (!TextUtils.isEmpty(text)) {
            ((TextView)itemView.findViewById(id)).setText(text);
        }
        return this;
    }

    /**
     * set color for text
     * @param id id
     * @param colorId resId
     * @return this
     */
    @SuppressLint("ResourceType")
    public SimpleViewHolder setTextColor(int id, int colorId) {
        TextView tv = (TextView) itemView.findViewById(id);
        tv.setTextColor(ContextCompat.getColor(itemView.getContext(), colorId));
        return this;
    }

    /**
     * this method will call 'setBackgroundResource()' from View
     * @param id id
     * @param resId resourceId
     * @return this
     */
    public SimpleViewHolder setBackgroundResource(int id, int resId) {
        itemView.findViewById(id).setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置背景颜色
     */
    public SimpleViewHolder setBackgroundColor(int id, int color) {
        itemView.findViewById(id).setBackgroundColor(color);
        return this;
    }

    /**
     * 设置imageView
     */
    public SimpleViewHolder setImageResource(int id, int resId) {
        ImageView imageView = (ImageView) itemView.findViewById(id);
        imageView.setImageResource(resId);
        return this;
    }

    /**
     * 为imageView 设置bitmap
     */
    public SimpleViewHolder setImageBitmap(int id, Bitmap bitmap) {
        ImageView imageView = (ImageView) itemView.findViewById(id);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    /**
     * set gone
     */
    public SimpleViewHolder setGone(int id, boolean isVisible) {
        if (!isVisible) {
            itemView.findViewById(id).setVisibility(View.GONE);
        } else {
            itemView.findViewById(id).setVisibility(View.VISIBLE);
        }
        return this;
    }

    /**
     * set visible
     */
    public SimpleViewHolder setVisible(int id, boolean isVisible) {
        if (isVisible) itemView.findViewById(id).setVisibility(View.VISIBLE);
        else itemView.findViewById(id).setVisibility(View.INVISIBLE);
        return this;
    }

    /**
     * findViewById
     */
    public <T extends View> T getView(@IdRes int id) {
        View view = itemView.findViewById(id);
        return (T) view;
    }
}

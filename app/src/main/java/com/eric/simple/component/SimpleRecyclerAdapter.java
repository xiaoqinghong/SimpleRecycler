package com.eric.simple.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class SimpleRecyclerAdapter<T> extends RecyclerView.Adapter<SimpleViewHolder> {

    private List<T> mData;
    private int mLayoutId;
    private RecyclerView mRecyclerView;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnSubViewClickListener onSubViewClickListener;
    private OnSubViewLongClickListener onSubViewLongClickListener;

    public SimpleRecyclerAdapter(int layout, List<T> list) {
        this.mLayoutId = layout;
        this.mData = list;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View itemView = LayoutInflater.from(context).inflate(mLayoutId, viewGroup, false);
        SimpleViewHolder simpleViewHolder = new SimpleViewHolder(itemView);
        simpleViewHolder.bindItemClickListener(onItemClickListener);
        simpleViewHolder.bindSubViewClickListener(onSubViewClickListener);
        simpleViewHolder.bindItemLongClickListener(onItemLongClickListener);
        simpleViewHolder.bindSubViewLongClickListener(onSubViewLongClickListener);
        return simpleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder simpleViewHolder, int i) {
        simpleViewHolder.itemView.setTag(SimpleViewHolder.ITEM_POSITION_KEY, i);
        simpleViewHolder.itemView.setTag(SimpleViewHolder.ITEM_IS_ITEM_KEY, true);
        bindData(simpleViewHolder, mData.get(i));
    }

    @Override
    public int getItemCount() {
        if (mData != null) return mData.size();
        else return 0;
    }

    /**
     * 子类需要实现的抽象方法，用于装填数据
     */
    protected abstract void bindData(SimpleViewHolder helper, T item);

    /**
     * 获取Context
     */
    public Context getContext() {
        return context;
    }

    /**
     * 绑定RecyclerView
     */
    public void bindRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        this.mRecyclerView.setAdapter(this);
    }

    /**
     * 设置item的点击事件监听
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 设置item的长按事件监听
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    /**
     * 设置subView 的点击事件监听
     */
    public void setOnSubViewClickListener(OnSubViewClickListener onSubViewClickListener) {
        this.onSubViewClickListener = onSubViewClickListener;
    }

    /**
     * 设置subView 的长按事件监听
     */
    public void setOnSubViewLongClickListener(OnSubViewLongClickListener onSubViewLongClickListener) {
        this.onSubViewLongClickListener = onSubViewLongClickListener;
    }

    /**
     * item click
     */
    public interface OnItemClickListener {
        void onClick(View v, int position);
    }

    /**
     * item long click
     */
    public interface OnItemLongClickListener{
        void onLongClick(View v, int position);
    }

    /**
     * subView click
     */
    public interface OnSubViewClickListener {
        void onClick(View v, int position);
    }

    /**
     * subView long click
     */
    public interface OnSubViewLongClickListener {
        void onLongClick(View v, int position);
    }
}

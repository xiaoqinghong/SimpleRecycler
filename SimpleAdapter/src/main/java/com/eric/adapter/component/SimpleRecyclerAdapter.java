package com.eric.adapter.component;

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
    private View mHeaderView;
    private View mFooterView;
    private RecyclerView mRecyclerView;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnSubViewClickListener onSubViewClickListener;
    private OnSubViewLongClickListener onSubViewLongClickListener;

    //Type
    private static int TYPE_NORMAL = 1000;
    private static int TYPE_HEADER = 1001;
    private static int TYPE_FOOTER = 1002;

    public SimpleRecyclerAdapter(int layout, List<T> list) {
        this.mLayoutId = layout;
        this.mData = list;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView;
        SimpleViewHolder simpleViewHolder;
        if (viewType == TYPE_HEADER) {
            itemView = mHeaderView;
        } else if (viewType == TYPE_FOOTER) {
            itemView = mFooterView;
        } else {
            itemView = LayoutInflater.from(context).inflate(mLayoutId, viewGroup, false);
        }
        simpleViewHolder = new SimpleViewHolder(itemView);
        simpleViewHolder.setHaveHeader(haveHeader());
        simpleViewHolder.bindItemClickListener(onItemClickListener);
        simpleViewHolder.bindSubViewClickListener(onSubViewClickListener);
        simpleViewHolder.bindItemLongClickListener(onItemLongClickListener);
        simpleViewHolder.bindSubViewLongClickListener(onSubViewLongClickListener);
        return simpleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder simpleViewHolder, int position) {
        if (!isHeader(position) && !isFooter(position)){
            if (haveHeader()) position --;
            simpleViewHolder.itemView.setTag(SimpleViewHolder.KEY_IS_ITEM, true);
            bindData(simpleViewHolder, mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        int count = mData != null? mData.size() : 0;
        if (mHeaderView != null) count ++;
        if (mFooterView != null) count ++;
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return TYPE_HEADER;
        }
        if (isFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    private boolean haveHeader() {
        return mHeaderView != null;
    }

    private boolean haveFooter() {
        return mFooterView != null;
    }

    private boolean isHeader(int position) {
        return haveHeader() && position == 0;
    }

    private boolean isFooter(int position) {
        return haveFooter() && position == getItemCount() - 1;
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
        if (mRecyclerView != null) return;
        this.mRecyclerView = recyclerView;
        this.mRecyclerView.setAdapter(this);
        this.context = mRecyclerView.getContext();
    }

    /**
     * 绑定RecyclerView
     * RecyclerView绑定layoutManager
     */
    public void bindRecyclerView(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager){
        if (mRecyclerView != null) return;
        this.mRecyclerView = recyclerView;
        this.mRecyclerView.setLayoutManager(layoutManager);
        this.mRecyclerView.setAdapter(this);
        this.context = mRecyclerView.getContext();
    }

    /**
     * 给整个列表添加header
     */
    public void addHeaderView(View view) {
        if (this.mHeaderView != null) return;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.mHeaderView = view;
        mHeaderView.setLayoutParams(params);

    }

    /**
     * 给整个列表添加footer
     */
    public void addFooterView(View view) {
        if (this.mFooterView != null) return;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.mFooterView = view;
        mFooterView.setLayoutParams(params);
    }

    //================以下是属性设置======================//

    /**
     * 设置item的点击事件监听
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (this.onItemClickListener != null) return;
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 设置item的长按事件监听
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        if (this.onItemLongClickListener != null) return;
        this.onItemLongClickListener = onItemLongClickListener;
    }

    /**
     * 设置subView 的点击事件监听
     */
    public void setOnSubViewClickListener(OnSubViewClickListener onSubViewClickListener) {
        if (this.onSubViewClickListener != null) return;
        this.onSubViewClickListener = onSubViewClickListener;
    }

    /**
     * 设置subView 的长按事件监听
     */
    public void setOnSubViewLongClickListener(OnSubViewLongClickListener onSubViewLongClickListener) {
        if (this.onSubViewLongClickListener != null) return;
        this.onSubViewLongClickListener = onSubViewLongClickListener;
    }

    //================以下是事件监听的回调接口===================//
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
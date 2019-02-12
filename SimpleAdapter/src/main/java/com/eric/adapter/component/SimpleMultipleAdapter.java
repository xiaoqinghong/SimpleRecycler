package com.eric.adapter.component;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eric.adapter.listener.OnItemClickListener;
import com.eric.adapter.listener.OnItemLongClickListener;
import com.eric.adapter.listener.OnSubViewClickListener;
import com.eric.adapter.listener.OnSubViewLongClickListener;

import java.util.List;

/**
 * Author: Eric
 * Date: Created in 2019/2/12 11:26 AM
 * Description： 多种itemView的Adapter
 */
public abstract class SimpleMultipleAdapter<T extends SimpleMultipleAdapter.TypeBind> extends RecyclerView.Adapter<SimpleViewHolder> {
    private List<T> mData;
    @SuppressLint("UseSparseArrays")
    private SparseArray<Integer> mLayouts = new SparseArray<>();
    private View mHeaderView;
    private View mFooterView;
    private RecyclerView mRecyclerView;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnSubViewClickListener onSubViewClickListener;
    private OnSubViewLongClickListener onSubViewLongClickListener;
    private static int TYPE_HEADER = 1001;
    private static int TYPE_FOOTER = 1002;

    public SimpleMultipleAdapter(List<T> list) {
        this.mData = list;
    }

    /**
     * 添加多种item的layout，并传入对应的type
     * type作为K，layout作为V
     * 在构造方法处调用，可以多次调用
     */
    public void addLayout(@LayoutRes int resId, int type) {
        mLayouts.put(type, resId);
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
            itemView = LayoutInflater.from(context).inflate(mLayouts.get(viewType), viewGroup, false);
        }
        simpleViewHolder = new SimpleViewHolder(itemView);
        if (viewType != TYPE_HEADER && viewType != TYPE_FOOTER) {
            simpleViewHolder.setHaveHeader(haveHeader());
            simpleViewHolder.bindItemClickListener(onItemClickListener);
            simpleViewHolder.bindSubViewClickListener(onSubViewClickListener);
            simpleViewHolder.bindItemLongClickListener(onItemLongClickListener);
            simpleViewHolder.bindSubViewLongClickListener(onSubViewLongClickListener);
        }
        return simpleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder simpleViewHolder, int position) {
        if (!isHeader(position) && !isFooter(position)){
            if (haveHeader()) position --;
            simpleViewHolder.itemView.setTag(SimpleViewHolder.KEY_IS_ITEM, true);
            multipleBind(simpleViewHolder, mData.get(position), mData.get(position).type());
        }
    }

    abstract protected void multipleBind(SimpleViewHolder helper, T item, int itemType);

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
        if (haveHeader()) position--;
        return mData.get(position).type();
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
     * bean类区分类型的接口
     */
    public interface TypeBind {
        int type();
    }

    //================ 以下是外部调用 ==============//
    /**
     * 绑定RecyclerView，默认LinearLayoutManager
     */
    public void bindRecyclerView(RecyclerView recyclerView) {
        if (this.mRecyclerView != null) return;
        this.mRecyclerView = recyclerView;
        if (mRecyclerView.getLayoutManager() == null){
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        }
        this.mRecyclerView.setAdapter(this);
        this.context = mRecyclerView.getContext();
    }

    /**
     * 绑定RecyclerView
     * RecyclerView绑定layoutManager
     */
    public void bindRecyclerView(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager){
        if (this.mRecyclerView != null) return;
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

    /**
     * 在adapter里面调用
     */
    public Context getContext() {
        return context;
    }
}

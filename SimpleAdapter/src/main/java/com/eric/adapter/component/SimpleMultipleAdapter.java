package com.eric.adapter.component;

import android.annotation.SuppressLint;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author: Eric
 * Date: Created in 2019/2/12 11:26 AM
 * Description： 多种itemView的Adapter
 */
public abstract class SimpleMultipleAdapter<T extends SimpleMultipleAdapter.TypeBind>
        extends SimpleRecyclerAdapter<T> {
    private List<T> mData;
    @SuppressLint("UseSparseArrays")
    private SparseArray<Integer> mLayouts = new SparseArray<>();

    public SimpleMultipleAdapter(List<T> list) {
        super(0, list);
        this.mData = list;
    }

    /**
     * 添加多种item的layout，并传入对应的type
     * type作为K，layout作为V
     * 在构造方法处调用，可以多次调用
     */
    protected void addLayout(int type, @LayoutRes int resId) {
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

    /**
     * bean类区分类型的接口
     */
    public interface TypeBind {
        int type();
    }
}

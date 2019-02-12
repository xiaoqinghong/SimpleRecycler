package com.eric.simple.adapter;

import com.eric.adapter.component.SimpleMultipleAdapter;
import com.eric.adapter.component.SimpleViewHolder;
import com.eric.simple.R;

import java.util.List;

/**
 * Author: Eric
 * Date: Created in 2019/2/12 1:24 PM
 * Description： SimpleMultipleAdapter例子
 */
public class MultipleTestAdapter extends SimpleMultipleAdapter<MultipleBean> {
    public MultipleTestAdapter(List<MultipleBean> list) {
        super(list);
        addLayout(R.layout.multiple_item_a, 0);
        addLayout(R.layout.multiple_item_b, 1);
        addLayout(R.layout.multiple_item_c, 2);
    }

    @Override
    protected void multipleBind(SimpleViewHolder helper, MultipleBean item, int itemType) {
        switch (itemType) {
            case 0:
                helper.setText(R.id.tvMultipleA, "this is type "+item.getName());
                break;
            case 1:
                helper.setText(R.id.tvMultipleB, "this is type "+item.getName());
                break;
            case 2:
                helper.setText(R.id.tvMultipleC, "this is type "+item.getName());
                break;
            default:
                break;
        }
    }
}

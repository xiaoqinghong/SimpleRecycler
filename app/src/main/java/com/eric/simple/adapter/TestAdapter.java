package com.eric.simple.adapter;

import com.eric.simple.R;
import com.eric.simple.component.SimpleRecyclerAdapter;
import com.eric.simple.component.SimpleViewHolder;

import java.util.List;

public class TestAdapter extends SimpleRecyclerAdapter<String> {

    public TestAdapter(List<String> list) {
        super(R.layout.item_test, list);
    }

    @Override
    protected void bindData(SimpleViewHolder helper, String item) {
        helper.setText(R.id.tv_test_item, item)
                .addClickListener(R.id.tv_test_item)
                .addLongClickListener(R.id.tv_test_item)
                .getView(R.id.tv_test_item);
    }
}

package com.eric.simple.adapter;

import com.eric.adapter.component.SimpleRecyclerAdapter;
import com.eric.adapter.component.SimpleViewHolder;
import com.eric.simple.R;

import java.util.List;

public class TestAdapter extends SimpleRecyclerAdapter<String> {

    public TestAdapter(List<String> list) {
        super(R.layout.item_test, list);
    }

    @Override
    protected void bind(SimpleViewHolder helper, String item) {
        helper.setText(R.id.tv_test_item, item)
                .addClickListener(R.id.tv_test_item)
                .addLongClickListener(R.id.tv_test_item)
                .setTextColor(R.id.tv_test_item, R.color.colorAccent);
    }
}

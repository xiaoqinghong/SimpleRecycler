package com.eric.simplerecyler.adapter;

import com.eric.simplerecyler.R;
import com.eric.simplerecyler.component.SimpleRecyclerAdapter;
import com.eric.simplerecyler.component.SimpleViewHolder;

import java.util.List;

public class TestAdapter extends SimpleRecyclerAdapter<String> {

    public TestAdapter(List<String> list) {
        super(R.layout.item_test, list);
    }

    @Override
    protected void bindData(SimpleViewHolder helper, String item) {
        helper.setText(R.id.tv_test_item, item)
                .addClickListener(R.id.tv_test_item)
                .getView(R.id.tv_test_item);
    }
}

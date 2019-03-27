package com.eric.simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.eric.adapter.listener.OnItemClickListener;
import com.eric.adapter.listener.OnItemLongClickListener;
import com.eric.adapter.listener.OnSubViewClickListener;
import com.eric.adapter.listener.OnSubViewLongClickListener;
import com.eric.simple.adapter.TestAdapter;

import java.util.ArrayList;

public class SingleTypeAdapterActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TestAdapter mAdapter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvMain);
        initRecycler();
        initEvent();
        loadData();
    }

    private void initRecycler() {
        View header = View.inflate(this, R.layout.header_test, null);
        View footer = View.inflate(this, R.layout.footer_test, null);
        list = new ArrayList<>();
        mAdapter = new TestAdapter(list);
        mAdapter.bindRecyclerView(recyclerView, new LinearLayoutManager(this));
        mAdapter.addHeaderView(header);
        mAdapter.addFooterView(footer);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleTypeAdapterActivity.this, MultipleTypeActivity.class));
            }
        });
    }

    private void initEvent() {
        // click
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(SingleTypeAdapterActivity.this, "item" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOnSubViewClickListener(new OnSubViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(SingleTypeAdapterActivity.this, "sub item" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        // long click
        mAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public void onLongClick(View v, int position) {

            }
        });

        mAdapter.setOnSubViewLongClickListener(new OnSubViewLongClickListener() {
            @Override
            public void onLongClick(View v, int position) {

            }
        });
    }

    private void loadData() {
        for (int i = 0; i < 20; i++) {
            list.add("点击文字是sub item " + i);
        }
        mAdapter.notifyDataSetChanged();
    }
}

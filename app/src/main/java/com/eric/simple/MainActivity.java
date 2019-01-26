package com.eric.simple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.eric.adapter.component.SimpleRecyclerAdapter;
import com.eric.simple.adapter.TestAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
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
    }

    private void initEvent() {
        // click
        mAdapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(MainActivity.this, "item"+position, Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOnSubViewClickListener(new SimpleRecyclerAdapter.OnSubViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(MainActivity.this, "sub item"+position, Toast.LENGTH_SHORT).show();
            }
        });

        // long click
        mAdapter.setOnItemLongClickListener(new SimpleRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(View v, int position) {

            }
        });

        mAdapter.setOnSubViewLongClickListener(new SimpleRecyclerAdapter.OnSubViewLongClickListener() {
            @Override
            public void onLongClick(View v, int position) {

            }
        });
    }

    private void loadData() {
        for (int i = 0; i < 20; i++) {
            list.add("点击文字是sub item "+i);
        }
        mAdapter.notifyDataSetChanged();
    }
}

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
    private RecyclerView rvMain;
    private TestAdapter testAdapter;
    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvMain = findViewById(R.id.rvMain);
        initRecycler();
        initEvent();
        loadData();
    }

    private void initRecycler() {
        list = new ArrayList<>();
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        testAdapter = new TestAdapter(list);
        testAdapter.bindRecyclerView(rvMain);
        View header = View.inflate(this, R.layout.header_test, null);
        View footer = View.inflate(this, R.layout.footer_test, null);
        testAdapter.addHeaderView(header);
        testAdapter.addFooterView(footer);
    }

    private void initEvent() {
        // click
        testAdapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(MainActivity.this, "item"+position, Toast.LENGTH_SHORT).show();
            }
        });

        testAdapter.setOnSubViewClickListener(new SimpleRecyclerAdapter.OnSubViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(MainActivity.this, "sub item"+position, Toast.LENGTH_SHORT).show();
            }
        });

        // long click
        testAdapter.setOnItemLongClickListener(new SimpleRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(View v, int position) {

            }
        });

        testAdapter.setOnSubViewLongClickListener(new SimpleRecyclerAdapter.OnSubViewLongClickListener() {
            @Override
            public void onLongClick(View v, int position) {

            }
        });
    }

    private void loadData() {
        for (int i = 0; i < 20; i++) {
            list.add("点击文字是sub item "+i);
        }
        testAdapter.notifyDataSetChanged();
    }
}

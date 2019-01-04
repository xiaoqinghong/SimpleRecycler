package com.eric.simplerecyler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.eric.simplerecyler.adapter.TestAdapter;
import com.eric.simplerecyler.component.SimpleRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

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
    }

    private void initEvent() {
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
    }

    private void loadData() {
        for (int i = 0; i < 20; i++) {
            list.add("文字是sub item "+i);
        }
        testAdapter.notifyDataSetChanged();
    }
}

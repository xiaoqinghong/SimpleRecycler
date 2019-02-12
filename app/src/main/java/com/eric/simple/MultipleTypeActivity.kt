package com.eric.simple

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.eric.simple.adapter.MultipleBean
import com.eric.simple.adapter.MultipleTestAdapter
import kotlinx.android.synthetic.main.activity_multiple.*

class MultipleTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple)
        initRecycler()
    }

    private fun initRecycler() {
        val adapter = MultipleTestAdapter(loadList())
        adapter.bindRecyclerView(rvMultiple)
        adapter.addHeaderView(View.inflate(this, R.layout.header_test, null))
        adapter.addFooterView(View.inflate(this, R.layout.footer_test, null))
        adapter.setOnItemClickListener { _, position ->
            Toast.makeText(this, "click position $position", Toast.LENGTH_SHORT).show()
        }
        adapter.setOnItemLongClickListener { _, position ->
            Toast.makeText(this, "long click position $position", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadList(): List<MultipleBean> {
        val list = mutableListOf<MultipleBean>()
        list.add(MultipleBean(0, "A"))
        list.add(MultipleBean(1, "B"))
        list.add(MultipleBean(2, "C"))
        list.add(MultipleBean(0, "A"))
        list.add(MultipleBean(1, "B"))
        list.add(MultipleBean(2, "C"))
        list.add(MultipleBean(2, "C"))
        list.add(MultipleBean(2, "C"))
        list.add(MultipleBean(2, "C"))
        list.add(MultipleBean(1, "B"))
        list.add(MultipleBean(2, "C"))
        list.add(MultipleBean(0, "A"))
        list.add(MultipleBean(1, "B"))
        list.add(MultipleBean(2, "C"))
        return list
    }
}

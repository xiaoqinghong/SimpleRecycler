# SimpleRecycler
apk：https://github.com/xiaoqinghong/SimpleRecycler/raw/master/app/release/app-release.apk  
### step：
```groovy
步骤1.将JitPack存储库添加到构建文件中
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}

步骤2.添加依赖项
dependencies {
	        implementation 'com.github.xiaoqinghong:SimpleRecycler:0.0.1'
	}
```
### Adapter写法
```java
public class TestAdapter extends SimpleRecyclerAdapter<String> {

    public TestAdapter(List<String> list) {
        super(R.layout.item_test, list);
    }

    @Override
    protected void bindData(SimpleViewHolder helper, String item) {
        helper.setText(R.id.tv_test_item, item)
                .addClickListener(R.id.tv_test_item) // 设置子项的点击事件监听
                .addLongClickListener(R.id.tv_test_item) // 设置子项的长按事件监听
                .getView(R.id.tv_test_item);
    }
}
```
### adapter与recyclerView  

```java
View header = View.inflate(this, R.layout.header_test, null);
View footer = View.inflate(this, R.layout.footer_test, null);
list = new ArrayList<>();
mAdapter = new TestAdapter(list);
mAdapter.bindRecyclerView(recyclerView, new LinearLayoutManager(this));
mAdapter.addHeaderView(header);
mAdapter.addFooterView(footer);
// click
mAdapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener() {
    @Override
    public void onClick(View v, int position) {
        
    }
});

mAdapter.setOnSubViewClickListener(new SimpleRecyclerAdapter.OnSubViewClickListener() {
    @Override
    public void onClick(View v, int position) {
        
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
```

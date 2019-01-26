# SimpleRecycler
apk：https://github.com/xiaoqinghong/SimpleRecycler/raw/master/app/release/app-release.apk  
### step：
1. 在工程目录的```com.eric.simple.component```下找到SimpleRecyclerAdapter &amp; SimpleViewHolder；
2. 把这两个文件放到自己的工程中（放到哪个位置自定义）；
3. RecyclerView的adapter继承```SimpleRecyclerAdapter<T>```并在泛型处传入数据类；
4. 仅在bindData中使用helper来操作每一个item；  
ps. 这里的helper是一个链式调用。helper即是SimpleViewHolder。当前这个demo中只封装了一些常用的方法。
比如：设置可见性、设置文字、设置文字颜色、设置图片、点击、长按、通过id获取view等，getView()方法可以获取item中的任意view。
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

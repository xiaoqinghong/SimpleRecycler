# SimpleRecycler
### step：
1. 在工程目录的```com.eric.simple.component```下找到SimpleRecyclerAdapter &amp; SimpleViewHolder；
2. 把这连个文件放到自己的工程中（放到哪个位置自定义）；
3. RecyclerView的adapter继承```SimpleRecyclerAdapter<T>```并再泛型处传入数据bean类；
4. 仅在bindData中使用helper来操作每一个item；
ps. 这里的helper是一个链式调用。helper即是SimpleViewHolder。当前这个demo中只封装了一些常用的方法。
比如：设置可见性、设置文字、设置文字颜色、设置图片、点击、长按、通过id获取view等。若需要别的方法，可自行添加。
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
### Activity中的recyclerView
```java
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
        // 绑定RecyclerView
        testAdapter.bindRecyclerView(rvMain);
    }

    private void initEvent() {
        // item 设置点击监听
        testAdapter.setOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                // item 点击监听回调
                Toast.makeText(MainActivity.this, "item"+position, Toast.LENGTH_SHORT).show();
            }
        });
        // 子项设置点击监听
        testAdapter.setOnSubViewClickListener(new SimpleRecyclerAdapter.OnSubViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                // 子项点击监听回调
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
```

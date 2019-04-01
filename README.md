# SimpleRecycler
apk：https://github.com/xiaoqinghong/SimpleRecycler/raw/master/app/release/app-release.apk

### SimpleAdapter（只支持一种itemView）
```java
public class TestAdapter extends SimpleRecyclerAdapter<String> {

    public TestAdapter(List<String> list) {
        super(R.layout.item_test, list);
    }

    @Override
    protected void bind(SimpleViewHolder helper, String item) {
        helper.setText(R.id.tv_test_item, item)
                .addClickListener(R.id.tv_test_item) // 设置子项的点击事件监听
                .addLongClickListener(R.id.tv_test_item) // 设置子项的长按事件监听
                .getView(R.id.tv_test_item);
    }
}
```  
### MultipleAdapter（支持多种itemView）  
```java
/**
* bean类需要实现SimpleMultipleAdapter.TypeBind接口。每个bean类都可能属于不同的type
*/
public class MultipleBean implements SimpleMultipleAdapter.TypeBind {
    @Override
    public int type() {
        return 0;
    }
}
```
```
/**
* MultipleTestAdapter
*/
public class MultipleTestAdapter extends SimpleMultipleAdapter<MultipleBean> {
    public MultipleTestAdapter(List<MultipleBean> list) {
        super(list);
        // 添加多种item对应的layout。
        addLayout(0, R.layout.multiple_item_a);
        addLayout(1, R.layout.multiple_item_b);
        addLayout(2, R.layout.multiple_item_c);
    }

    @Override
    protected void bind(SimpleViewHolder helper, MultipleBean item) {
        switch (itemType) {
            case 0:
                // doSomething...
                break;
            case 1:
                // doSomething...
                break;
            case 2:
               // doSomething...
                break;
            default:
                // doSomething...
                break;
        }
    }
}
```

### adapter暴露的接口

```java
mAdapter.bindRecyclerView(recyclerView); // 内部默认使用LinearLayoutManager
mAdapter.addHeaderView(header);
mAdapter.addFooterView(footer);
// click
mAdapter.setOnItemClickListener();

mAdapter.setOnSubViewClickListener();

// long click
mAdapter.setOnItemLongClickListener();

mAdapter.setOnSubViewLongClickListener();

```
### 使用方式1：
下载工程源码，并将SimpleAdapter模块作为model导入到工程中

### 使用方式2：
步骤1.将JitPack存储库添加到构建文件中
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
步骤2.添加依赖项
```groovy
dependencies {
	implementation 'com.github.xiaoqinghong:SimpleRecycler:last-version'
}
```

### LICENSE
```
Copyright (c) 2018-present, SimpleRecycler Contributors.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

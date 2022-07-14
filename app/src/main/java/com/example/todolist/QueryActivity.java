package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.adapter.MyAdapter;
import com.example.todolist.bean.TodoList;

import java.util.ArrayList;
import java.util.List;

public class QueryActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;


    private MyAdapter mAdapter;
    private RecyclerView mList;
    private List<TodoList> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        dbHelper = new MyDatabaseHelper(this);

        mList = (RecyclerView) this.findViewById(R.id.recycler_view);

        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this,R.drawable.shape));
        mList.addItemDecoration(divider);

        initData();
        showList();

    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                TodoList todoList = mData.get(position);

                //这里处理点击事件
                Intent intent = new Intent(QueryActivity.this,EditActivity.class);
                intent.putExtra("todolist",todoList);
                startActivity(intent);
            }
        });
    }


    private void showList() {
        //RecyclerView需要设置样式，实质是布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //设置布局管理器
        mList.setLayoutManager(layoutManager);
        //创建适配器
        mAdapter = new MyAdapter(mData);
        //设置到RecyclerView中
        mList.setAdapter(mAdapter);

        initListener();

    }

    //用于模拟数据
    private void initData(){
        //创建数据集合
        mData = new ArrayList<>();
        dbHelper = new MyDatabaseHelper(this);
        mData = getDataFromDb();
    }
    public List<TodoList> getDataFromDb() {
        return dbHelper.queryAllFromDb();
    }
}

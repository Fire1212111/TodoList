package com.example.todolist;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.adapter.MyAdapter;
import com.example.todolist.bean.TodoList;

import java.util.List;

public class AddActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    private List<TodoList> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbHelper = new MyDatabaseHelper(this);

        EditText etTitle = (EditText) findViewById(R.id.et_title);
        EditText etContent = (EditText) findViewById(R.id.et_content);
        Button  etAdd = (Button) findViewById(R.id.et_add);

        etAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                if(title.isEmpty()){
                    Toast.makeText(AddActivity.this,"标题不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(content.isEmpty()){
                    Toast.makeText(AddActivity.this,"内容不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                TodoList todoList = new TodoList();
                todoList.setTitle(title);
                todoList.setContent(content);
                todoList.setCreatedTime("创建时间："+todoList.getCurrentTimeFormat());
                todoList.setStatus("未完成");
                todoList.setUpdatedTime("未更新");
                dbHelper.insertData(todoList);
                Toast.makeText(AddActivity.this,"已添加",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}

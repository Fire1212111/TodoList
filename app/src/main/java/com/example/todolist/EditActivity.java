package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todolist.bean.TodoList;

import java.util.List;

public class EditActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    private List<TodoList> mData;
    private TodoList todoList;

    EditText etTitle,etContent;
    Button etEdit,etStatus,etDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etTitle = (EditText) findViewById(R.id.et_title);
        etContent = (EditText) findViewById(R.id.et_content);
        etEdit = (Button) findViewById(R.id.et_edit);
        etStatus = (Button) findViewById(R.id.et_status);
        etDelete = (Button) findViewById(R.id.et_delete);

        initData();

        etEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                if(title.isEmpty()){
                    Toast.makeText(EditActivity.this,"标题不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(content.isEmpty()){
                    Toast.makeText(EditActivity.this,"内容不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                todoList.setTitle(title);
                todoList.setContent(content);
                todoList.setUpdatedTime("最近更新："+todoList.getCurrentTimeFormat());
                dbHelper.updateData(todoList);
                Toast.makeText(EditActivity.this,"已修改",Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(EditActivity.this,QueryActivity.class);
                startActivity(intent);
            }
        });

        etStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoList.setStatus("已完成");
                dbHelper.updateData(todoList);
                Toast.makeText(EditActivity.this,"已完成",Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(EditActivity.this,QueryActivity.class);
                startActivity(intent);
            }
        });

        etDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteFromDbById(todoList.getId());
                Toast.makeText(EditActivity.this,"已删除",Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(EditActivity.this,QueryActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initData() {
        Intent intent = getIntent();
        todoList = (TodoList) intent.getSerializableExtra("todolist");
        if (todoList != null) {
            etTitle.setText(todoList.getTitle());
            etContent.setText(todoList.getContent());
        }
        dbHelper = new MyDatabaseHelper(this);
    }
}

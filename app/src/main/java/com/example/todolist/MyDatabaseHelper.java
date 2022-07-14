package com.example.todolist;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.todolist.bean.TodoList;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "TodoListSQLite.db";
    private static final String TABLE_NAME_TODOLIST = "todolist";

    private static final String CREATE_TABLE_SQL = "create table " + TABLE_NAME_TODOLIST
            + " (id integer primary key autoincrement, title text, content text, create_time text, updated_time text, status text)";

    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertData(TodoList todoList){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",todoList.getTitle());
        values.put("content",todoList.getContent());
        values.put("create_time",todoList.getCreatedTime());
        values.put("updated_time",todoList.getUpdatedTime());
        values.put("status",todoList.getStatus());

        return db.insert(TABLE_NAME_TODOLIST,null,values);
    }

    public int deleteFromDbById(String id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME_TODOLIST,"id like ?",new String[]{id});
    }

    public int updateData(TodoList todoList){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",todoList.getTitle());
        values.put("content",todoList.getContent());
        values.put("create_time",todoList.getCreatedTime());
        values.put("updated_time",todoList.getUpdatedTime());
        values.put("status",todoList.getStatus());

        return db.update(TABLE_NAME_TODOLIST,values,"id like ?",new String[]{todoList.getId()});

    }

    public List<TodoList> queryAllFromDb(){
        SQLiteDatabase db = getWritableDatabase();
        List<TodoList> todoListList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_TODOLIST,null,null,null,null,null,null);
        if(cursor != null){
            while (cursor.moveToNext()){
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                @SuppressLint("Range") String createTime = cursor.getString(cursor.getColumnIndex("create_time"));
                @SuppressLint("Range") String updateTime = cursor.getString(cursor.getColumnIndex("updated_time"));
                @SuppressLint("Range") String status = cursor.getString(cursor.getColumnIndex("status"));

                TodoList todoList = new TodoList();
                todoList.setId(id);
                todoList.setTitle(title);
                todoList.setContent(content);
                todoList.setCreatedTime(createTime);
                todoList.setUpdatedTime(updateTime);
                todoList.setStatus(status);

                todoListList.add(todoList);
            }
            cursor.close();
        }
        return todoListList;
    }
}

package com.example.todolist.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.bean.TodoList;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.InnerHolder> {

    private final List<TodoList> mData;
    private OnItemClickListener mOnItemClickListener;

    public MyAdapter(List<TodoList>data){
        this.mData = data;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_list_view,null);

        return new InnerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {

        holder.setData(mData.get(position),position);
    }

    @Override
    public int getItemCount() {
        if(mData != null){
            return mData.size();
        }
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        //设置一个监听，其实是设置一个回调接口
        this.mOnItemClickListener = listener;
    }


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mContent;
        private TextView mStatus;
        private TextView mCreate_Time;
        private TextView mUpdate_Time;
        private int mPosition;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mContent = (TextView) itemView.findViewById(R.id.content);
            mStatus = (TextView) itemView.findViewById(R.id.status);
            mCreate_Time = (TextView) itemView.findViewById(R.id.create_time);
            mUpdate_Time = (TextView) itemView.findViewById(R.id.update_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.onItemClick(mPosition);
                    }
                }
            });
        }

        public void setData(TodoList todoList,int position) {
            this.mPosition = position;
            mTitle.setText(todoList.getTitle());
            mContent.setText(todoList.getContent());
            mStatus.setText(todoList.getStatus());
            mCreate_Time.setText(todoList.getCreatedTime());
            mUpdate_Time.setText(todoList.getUpdatedTime());
        }


    }
}

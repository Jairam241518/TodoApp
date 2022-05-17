package com.jairam.todoapp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jairam.todoapp.AddNewTask;
import com.jairam.todoapp.MainActivity;
import com.jairam.todoapp.Model.ToDoModel;
import com.jairam.todoapp.R;
import com.jairam.todoapp.Utils.DatabaseHandler;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder>{

    private List<ToDoModel> todoList;
    private MainActivity activity;
    private DatabaseHandler db;

    public ToDoAdapter(DatabaseHandler db, MainActivity activity){
        this.db = db;
        this.activity = activity;
    }

//    public ToDoAdapter(MainActivity activity) {
//        this.activity = activity;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        db.openDatabase();
        ToDoModel item = todoList.get(position);
        holder.taskText.setText(item.getTask());
        holder.progressBar.setProgress((int)(item.getInitialValue()*100 / item.getFinalValue()));
        holder.taskPercent.setText(item.getInitialValue() + "/" + item.getFinalValue());

    }
    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public void setTask(List<ToDoModel> todoList){
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public Context getContext(){
        return activity;
    }

//    public void setTask(List<ToDoModel> todoList){
//        this.todoList = todoList;
//    }

    public void deleteItem(int position){
        ToDoModel item = todoList.get(position);
        db.deleteTask(item.getIdTask());
        todoList.remove(position);
    }

    public void editItem(int position){
        ToDoModel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getIdTask());
        bundle.putInt("initialValue", item.getInitialValue());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        FloatingActionButton add;
        FloatingActionButton subtract;
        FloatingActionButton delete;
        TextView taskText;
        ProgressBar progressBar;
        TextView taskPercent;
        ViewHolder(View view){
            super(view);
            add = view.findViewById(R.id.addProgress);
            subtract = view.findViewById(R.id.subtractProgress);
            delete = view.findViewById(R.id.deleteTask);
            taskText = view.findViewById(R.id.taskData);
            progressBar = view.findViewById(R.id.progressBar);
            taskPercent = view.findViewById(R.id.taskPercentText);
        }


    }


}

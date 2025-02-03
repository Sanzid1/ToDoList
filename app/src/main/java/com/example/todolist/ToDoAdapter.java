package com.example.todolist;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

    private List<ToDoItem> toDoList;
    private Context context;

    public ToDoAdapter(Context context, List<ToDoItem> toDoList) {
        this.context = context;
        this.toDoList = toDoList;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        ToDoItem item = toDoList.get(position);
        holder.taskTextView.setText(item.getTask());
        holder.taskCheckBox.setChecked(item.isDone());

        // Update strike-through effect when checked
        if (item.isDone()) {
            holder.taskTextView.setPaintFlags(holder.taskTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.taskTextView.setPaintFlags(holder.taskTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        // Listen for checkbox changes to update model and UI
        holder.taskCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setDone(isChecked);
            if (isChecked) {
                holder.taskTextView.setPaintFlags(holder.taskTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.taskTextView.setPaintFlags(holder.taskTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        });
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public static class ToDoViewHolder extends RecyclerView.ViewHolder {
        TextView taskTextView;
        CheckBox taskCheckBox;

        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTextView = itemView.findViewById(R.id.taskTextView);
            taskCheckBox = itemView.findViewById(R.id.taskCheckBox);
        }
    }
}
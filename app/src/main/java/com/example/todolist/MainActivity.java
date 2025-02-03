package com.example.todolist;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ToDoAdapter adapter;
    private ArrayList<ToDoItem> toDoList;
    private EditText inputTask;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Link to activity_main.xml
        setContentView(R.layout.activity_main);

        // Find views by ID
        recyclerView = findViewById(R.id.recyclerView);
        inputTask = findViewById(R.id.inputTask);
        addButton = findViewById(R.id.addButton);

        // Initialize list and adapter
        toDoList = new ArrayList<>();
        adapter = new ToDoAdapter(this, toDoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Set up Add button click listener
        addButton.setOnClickListener(v -> {
            String task = inputTask.getText().toString().trim();
            if (!TextUtils.isEmpty(task)) {
                // Create a new task and update the adapter
                toDoList.add(new ToDoItem(task));
                adapter.notifyItemInserted(toDoList.size() - 1);
                inputTask.setText("");
                recyclerView.smoothScrollToPosition(toDoList.size() - 1);
            }
        });
    }
}
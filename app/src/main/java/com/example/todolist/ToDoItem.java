package com.example.todolist;

public class ToDoItem {
    private String task;
    private boolean isDone;

    public ToDoItem(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getTask() {
        return task;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
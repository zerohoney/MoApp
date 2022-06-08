package com.example.moapp.model;

public class TodoList {

    private String todoTime, todoWhat;

    public TodoList() {

    }

    public TodoList(String todoTime, String todoWhat) {
        this.todoTime = todoTime;
        this.todoWhat = todoWhat;
    }

    public String getTodoTime() {
        return todoTime;
    }

    public void setTodoTime(String todoTime) {
        this.todoTime = todoTime;
    }

    public String getTodoWhat() {
        return todoWhat;
    }

    public void setTodoWhat(String todoWhat) {
        this.todoWhat = todoWhat;
    }
}

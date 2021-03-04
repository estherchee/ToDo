package com.estherchee.todo;

import com.estherchee.todo.models.TaskCollection;

public class Main {
    public static void main(String[] input) {
        TaskCollection myTodo = new TaskCollection();
        myTodo.addTask("Hello", "2012-12-02", "personal");
        // Task test = new Task("Hello", "2012-12-02",  "personal");
        System.out.println(myTodo.getTasks());
    }
}

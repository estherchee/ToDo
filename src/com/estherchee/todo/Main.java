package com.estherchee.todo;

import com.estherchee.todo.models.TaskCollection;

public class Main {
    public static void main(String[] input) {
        TaskCollection myTodo = new TaskCollection();
        myTodo.addTask("Hello2", "2012-12-05", "personal");
        myTodo.addTask("Hello", "2012-12-02", "abc");
        myTodo.addTask("Hello3", "2012-12-03", "zasd");

        System.out.println(myTodo.getTasksByProject());
    }
}

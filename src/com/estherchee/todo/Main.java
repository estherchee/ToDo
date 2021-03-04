package com.estherchee.todo;

import com.estherchee.todo.models.Task;

public class Main {
    public static void main(String[] input) {
        Task test = new Task("Hello", "2012-12-02", "in progress", "personal");
        System.out.println(test);
    }
}
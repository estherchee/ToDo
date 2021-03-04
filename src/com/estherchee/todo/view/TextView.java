package com.estherchee.todo.view;

import com.estherchee.todo.model.TaskCollection;

import java.util.Scanner;

public class TextView {
    private final Scanner commandReader;
    private TaskCollection todos;
    private MainMenu currentMenu;

    public TextView() {
        commandReader = new Scanner(System.in);
        todos = new TaskCollection();
        todos.addTask("Hello2", "2012-12-05", "personal");
        todos.addTask("Hello", "2012-12-02", "abc");
        todos.addTask("Hello3", "2012-12-03", "zasd");
        currentMenu = new MainMenu(todos);
    }

    public void startup() {
        currentMenu.startup(commandReader, todos);
    }
}

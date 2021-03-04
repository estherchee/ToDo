package com.estherchee.todo.view;

import com.estherchee.todo.model.TaskCollection;

import java.util.Scanner;

public class TextView {
    private final Scanner commandReader;
    private final TaskCollection todos;
    private final MainMenu mainMenu;

    public TextView() {
        commandReader = new Scanner(System.in);
        todos = new TaskCollection();
        todos.addTask("Hello2", "2012-12-05", "personal");
        todos.addTask("Hello", "2012-12-02", "abc");
        todos.addTask("Hello3", "2012-12-03", "zasd");
        mainMenu = new MainMenu();
    }

    public void startup() {
        mainMenu.startup(commandReader, todos);
    }
}

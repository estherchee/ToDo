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
        mainMenu = new MainMenu();
    }

    public void startup() {
        mainMenu.startup(commandReader, todos);
    }
}

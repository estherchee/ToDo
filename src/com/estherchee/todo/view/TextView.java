package com.estherchee.todo.view;

import com.estherchee.todo.model.TaskCollection;
import com.estherchee.todo.repository.TaskIO;

import java.util.Scanner;

public class TextView {
    private final Scanner commandReader;
    private final TaskCollection todos;
    private final MainMenu mainMenu;
    private final TaskIO taskIO;

    public TextView() {
        taskIO = new TaskIO();
        todos = taskIO.readTasksDataFromDataFile();
        commandReader = new Scanner(System.in);
        mainMenu = new MainMenu();
    }

    public void startup() {
        mainMenu.startup(commandReader, todos);
        taskIO.writeTasksToFile(todos);
    }
}

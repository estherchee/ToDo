package com.estherchee.todo.view;

import com.estherchee.todo.model.TaskCollection;
import com.estherchee.todo.repository.TaskIO;

import java.util.Scanner;

/**
 * User interface for user interacting with the application.
 */
public class TextView {
    private final Scanner commandReader;
    private final TaskCollection todos;
    private final MainMenu mainMenu;
    private final TaskIO taskIO;

    /**
     * Initialise {@link TextView} object with {@link TaskIO} object, {@link Scanner} object and {@link MainMenu}
     * object. It also call {@link TaskIO#readTasksDataFromDataFile()} method to load data into system from file.
     */
    public TextView() {
        taskIO = new TaskIO();
        todos = taskIO.readTasksDataFromDataFile();
        commandReader = new Scanner(System.in);
        mainMenu = new MainMenu();
    }

    /**
     * Call {@link MainMenu#startup(Scanner, TaskCollection)} method to display main menu and save tasks into disk when
     * use exit from main menu.
     */
    public void startup() {
        mainMenu.startup(commandReader, todos);
        taskIO.writeTasksToFile(todos);
    }
}

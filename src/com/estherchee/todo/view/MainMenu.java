package com.estherchee.todo.view;

import com.estherchee.todo.model.TaskCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Main menu for user interacting with the application.
 */
public class MainMenu extends Menu {
    /**
     * Initialise {@link MainMenu} object with a list of choices.
     */
    public MainMenu() {
        super(new ArrayList<>(Arrays.asList(
                "Show task list (by date or project)",
                "Add new task",
                "Edit task (update, mark as done, remove)",
                "Save and quit"
        )));
    }

    private void getSummary(TaskCollection todos) {
        System.out.println("Here is a summary for you");
        System.out.println("Number of tasks todo            : " + todos.getNumberOfInProgressTask());
        System.out.println("Number of tasks are completed   : " + todos.getNumberOfCompletedTask());
    }


    /**
     * Display the available options with a status summary indicating number of tasks completed and number of task todo.
     *
     * @param todos A collection of <code>Task</code> object.
     */
    @Override
    void displayMenu(TaskCollection todos) {
        System.out.println("Welcome to ToDo!");
        displaySeparator();
        getSummary(todos);
        displaySeparator();
        getChoices();
    }

    /**
     * Run selected option according to user input.
     *
     * @param userChoice    Input of user.
     * @param commandReader <code>Scanner</code> object to get user input.
     * @param todos         A collection of <code>Task</code> object.
     */
    @Override
    public int executeMenuSwitcher(int userChoice, Scanner commandReader, TaskCollection todos) {
        if (userChoice != getChoiceNumberToExit()) {
            switch (userChoice) {
                case 1 -> {
                    ShowTasksMenu showTasksMenu = new ShowTasksMenu();
                    showTasksMenu.startup(commandReader, todos);
                }
                case 2 -> {
                    String title;
                    String dueDate;
                    String projectName;
                    System.out.println("Please enter title for the task...");
                    title = commandReader.nextLine();
                    System.out.println("Please enter due date for the task in format of (YYYY-MM-DD)...");
                    dueDate = commandReader.nextLine();
                    System.out.println("Please enter project name for the task...");
                    projectName = commandReader.nextLine();
                    todos.addTask(title, dueDate, projectName);
                    System.out.println("Your task has been created. Press enter to continue.");
                    commandReader.nextLine();
                }
                case 3 -> {
                    EditTaskMenu editTaskMenu = new EditTaskMenu();
                    editTaskMenu.startup(commandReader, todos);
                }
            }
            displaySeparator();
            displayMenu(todos);
            userChoice = -1;
        }
        return userChoice;
    }

    /**
     * Display main menu with options and capture user input for running of operation.
     */
    @Override
    public void startup(Scanner commandReader, TaskCollection todos) {
        displayMenu(todos);
        menuLoop(todos, commandReader, this);
        commandReader.close();
    }
}

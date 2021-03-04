package com.estherchee.todo.view;

import com.estherchee.todo.exception.InvalidCommandException;
import com.estherchee.todo.model.TaskCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainMenu extends Menu {
    public MainMenu(TaskCollection todos) {
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

    @Override
    void getMenu(TaskCollection todos) {
        System.out.println("Welcome to ToDo!");
        showSeparator();
        getSummary(todos);
        showSeparator();
        getChoices();
    }

    @Override
    public void startup(Scanner commandReader, TaskCollection todos) {
        getMenu(todos);
        int commandToExit = getChoiceNumberToExit();
        int userChoice = 0;
        while (userChoice != commandToExit) {
            try {
                String input = commandReader.nextLine();
                userChoice = Integer.parseInt(input);
                if (userChoice <= 0 || userChoice > getNumberOfChoices()) {
                    throw new InvalidCommandException(userChoice);
                } else {
                    switch (userChoice) {
                        case 1:
                            ShowTasksMenu menu = new ShowTasksMenu();
                            menu.startup(commandReader, todos);
                            userChoice = -1;
                            getMenu(todos);
                            break;
                        case 4:
                            commandReader.close();
                            break;
                    }
                }
            } catch (NumberFormatException | InvalidCommandException error) {
                System.out.println("throw1");
                String ANSI_RESET = "\u001B[0m";
                String ANSI_RED = "\u001B[31m";
                System.out.println(ANSI_RED + "Invalid input. Please try again." + ANSI_RESET);
            }
        }
    }
}

package com.estherchee.todo.view;

import com.estherchee.todo.exception.InvalidCommandException;
import com.estherchee.todo.model.TaskCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ShowTasksMenu extends Menu {
    public ShowTasksMenu() {
        super(new ArrayList<>(Arrays.asList(
                "Show task by date",
                "Show task by project",
                "Back to main menu"
        )));
    }

    @Override
    void getMenu(TaskCollection todos) {
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
                            showSeparator();
                            System.out.println(todos.getTasksByDate());
                            showSeparator();
                            System.out.println("Press enter to continue.");
                            commandReader.nextLine();
                            break;
                        case 2:
                            showSeparator();
                            System.out.println(todos.getTasksByProject());
                            showSeparator();
                            System.out.println("Press enter to continue.");
                            commandReader.nextLine();
                            break;
                        case 3:
                            break;
                    }
                    userChoice = commandToExit;
                }
            } catch (NumberFormatException | InvalidCommandException error) {
                System.out.println("throw5");
                String ANSI_RESET = "\u001B[0m";
                String ANSI_RED = "\u001B[31m";
                System.out.println(ANSI_RED + "Invalid input. Please try again." + ANSI_RESET);
            }
        }
    }
}

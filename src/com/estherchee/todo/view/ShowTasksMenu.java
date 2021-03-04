package com.estherchee.todo.view;

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
    public int executeMenuSwitcher(int userChoice, int commandToExit, Scanner commandReader, TaskCollection todos) {
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
        return commandToExit;
    }

    @Override
    public void startup(Scanner commandReader, TaskCollection todos) {
        getMenu(todos);
        menuLoop(todos, commandReader, this);
    }
}

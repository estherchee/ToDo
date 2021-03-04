package com.estherchee.todo.view;

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

    void getMenu(TaskCollection todos) {
        System.out.println("Welcome to ToDo!");
        showSeparator();
        getSummary(todos);
        showSeparator();
        getChoices();
    }

    @Override
    public int executeMenuSwitcher(int userChoice, int commandToExit, Scanner commandReader, TaskCollection todos) {
        switch (userChoice) {
            case 1:
                ShowTasksMenu menu = new ShowTasksMenu();
                menu.startup(commandReader, todos);
                getMenu(todos);
                userChoice = -1;
                break;
            case 4:
                userChoice = 4;
                break;
        }
        return userChoice;
    }

    @Override
    public void startup(Scanner commandReader, TaskCollection todos) {
        getMenu(todos);
        menuLoop(todos, commandReader, this);
        commandReader.close();
    }
}

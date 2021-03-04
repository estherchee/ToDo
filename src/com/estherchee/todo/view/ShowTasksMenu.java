package com.estherchee.todo.view;

import com.estherchee.todo.model.Task;
import com.estherchee.todo.model.TaskCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

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
        displaySeparator();
        getChoices();
    }

    private void displayTasks(List<Task> tasks) {
        int tasksSize = tasks.size();
        if (tasksSize > 0) {
            AtomicInteger taskIndex = new AtomicInteger(0);
            for (Task task : tasks) {
                System.out.println(task);
                if (taskIndex.get() + 1 < tasksSize) {
                    displaySeparator();
                }
                taskIndex.getAndIncrement();
            }
        } else {
            System.out.println("There is not tasks to display.");
        }
    }

    @Override
    public int executeMenuSwitcher(int userChoice, Scanner commandReader, TaskCollection todos) {
        switch (userChoice) {
            case 1:
                displaySeparator();
                displayTasks(todos.getTasksByDate());
                displaySeparator();
                System.out.println("Press enter to continue.");
                commandReader.nextLine();
                break;
            case 2:
                displaySeparator();
                displayTasks(todos.getTasksByProject());
                displaySeparator();
                System.out.println("Press enter to continue.");
                commandReader.nextLine();
                break;
            case 3:
                break;
        }
        return getChoiceNumberToExit();
    }

    @Override
    public void startup(Scanner commandReader, TaskCollection todos) {
        getMenu(todos);
        menuLoop(todos, commandReader, this);
    }
}

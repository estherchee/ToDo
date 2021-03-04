package com.estherchee.todo.view;

import com.estherchee.todo.model.TaskCollection;

import java.util.ArrayList;
import java.util.Arrays;

public class MainMenu extends Menu {
    private TaskCollection todos;

    public MainMenu(TaskCollection todos) {
        super(new ArrayList<>(Arrays.asList(
                "Show task list (by date or project)",
                "Add new task",
                "Edit task (update, mark as done, remove)",
                "Save and quit"
        )));
        this.todos = todos;
    }

    private void getSummary() {
        System.out.println("Here is a summary for you");
        System.out.println("Number of tasks todo            : " + todos.getNumberOfInProgressTask());
        System.out.println("Number of tasks are completed   : " + todos.getNumberOfCompletedTask());
    }

    public void getMainMenu() {
        System.out.println("Welcome to ToDo!");
        showSeparator();
        getSummary();
        showSeparator();
        getChoices();
        showSeparator();
    }
}

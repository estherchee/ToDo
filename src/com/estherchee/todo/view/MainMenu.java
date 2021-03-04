package com.estherchee.todo.view;

import com.estherchee.todo.model.TaskCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class MainMenu {
    private TaskCollection todos;
    private final ArrayList<String> choices = new ArrayList<String>(Arrays.asList(
            "Show task list (by date or project)",
            "Add new task",
            "Edit task (update, mark as done, remove)",
            "Save and quit"
    ));

    public MainMenu() {
        this.todos = new TaskCollection();
    }

    private void showSeparator() {
        System.out.println("");
    }

    private void getSummary() {
        System.out.println("Here is a summary for you");
        System.out.println("Number of tasks todo            : " + todos.getNumberOfInProgressTask());
        System.out.println("Number of tasks are completed   : " + todos.getNumberOfCompletedTask());
    }

    private void getChoices() {
        System.out.println("Please pick an option:");
        AtomicInteger choiceNumber = new AtomicInteger(1);
        for (String choice : this.choices) {
            System.out.println("(" + choiceNumber + ") " + choice);
            choiceNumber.getAndIncrement();
        }
    }

    public void getMainMenu() {
        System.out.println("Welcome to ToDo!");
        showSeparator();
        getSummary();
        showSeparator();
        getChoices();
    }
}

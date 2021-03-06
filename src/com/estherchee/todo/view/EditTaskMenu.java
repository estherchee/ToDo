package com.estherchee.todo.view;

import com.estherchee.todo.exception.InvalidCommandException;
import com.estherchee.todo.model.Task;
import com.estherchee.todo.model.TaskCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Menu for editing task details.
 */
public class EditTaskMenu extends Menu {
    /**
     * Initialise {@link EditTaskMenu} object with a list of choices. Available options including update task details
     * and remove task from collection.
     */
    public EditTaskMenu() {
        super(new ArrayList<>(Arrays.asList(
                "Update task details",
                "Update due date of task",
                "Update due date of project name",
                "Mark as done",
                "Remove task",
                "Back to main menu"
        )));
    }

    private void showTaskSummary(TaskCollection todos) {
        System.out.println("You have the following task");
        AtomicInteger taskNumber = new AtomicInteger(1);
        for (Task todo : todos.getTasks()) {
            System.out.println("(" + taskNumber + ") " + todo.getTitle());
            taskNumber.getAndIncrement();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startup(Scanner commandReader, TaskCollection todos) {
        displayMenu(todos);
        menuLoop(todos, commandReader, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void displayMenu(TaskCollection todos) {
        displaySeparator();
        getChoices();
    }

    private int getTaskNumberToUpdate(Scanner commandReader, String message, int taskLength) throws InvalidCommandException {
        System.out.println(message);
        int taskIndex = Integer.parseInt(commandReader.nextLine()) - 1;
        if (taskIndex < 0 || taskIndex + 1 > taskLength) {
            throw new InvalidCommandException(taskIndex);
        } else {
            return taskIndex;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    int executeMenuSwitcher(int userChoice, Scanner commandReader, TaskCollection todos) {
        if (userChoice != getChoiceNumberToExit()) {
            displaySeparator();
            showTaskSummary(todos);
            String message;
            int taskIndex;
            boolean isTaskIndexValid = false;
            int numberOfTasks = todos.getTasks().size();
            while (!isTaskIndexValid) {
                try {
                    switch (userChoice) {
                        case 1 -> {
                            message = "Please enter the task number to update title";
                            taskIndex = getTaskNumberToUpdate(commandReader, message, numberOfTasks);
                            System.out.println("Please enter new title");
                            String title = commandReader.nextLine();
                            todos.updateTaskTitle(taskIndex, title);
                        }
                        case 2 -> {
                            message = "Please enter the task number to update due date";
                            taskIndex = getTaskNumberToUpdate(commandReader, message, numberOfTasks);
                            System.out.println("Please enter new due date");
                            String dueDate = commandReader.nextLine();
                            todos.updateDueDate(taskIndex, dueDate);
                        }
                        case 3 -> {
                            message = "Please enter the task number to update project name";
                            taskIndex = getTaskNumberToUpdate(commandReader, message, numberOfTasks);
                            System.out.println("Please enter new project name");
                            String projectName = commandReader.nextLine();
                            todos.updateProjectName(taskIndex, projectName);
                        }
                        case 4 -> {
                            message = "Please enter the task number to mark as done";
                            taskIndex = getTaskNumberToUpdate(commandReader, message, numberOfTasks);
                            todos.markAsCompleted(taskIndex);
                        }
                        case 5 -> {
                            message = "Please enter the task number to remove";
                            taskIndex = getTaskNumberToUpdate(commandReader, message, numberOfTasks);
                            todos.removeTask(taskIndex);
                        }
                    }
                    isTaskIndexValid = true;
                } catch (InvalidCommandException error) {
                    System.err.println("No such task. Please try again.");
                }
            }
            displaySeparator();
            System.out.println("Information updated. Press enter to continue.");
            commandReader.nextLine();
        }

        return getChoiceNumberToExit();
    }
}

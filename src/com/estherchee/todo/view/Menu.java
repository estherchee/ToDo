package com.estherchee.todo.view;

import com.estherchee.todo.exception.InvalidCommandException;
import com.estherchee.todo.model.TaskCollection;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Abstract class to model the basic behaviour of menu object.
 */
abstract class Menu implements MenuSwitcher {
    final ArrayList<String> choices;

    /**
     * Initialise object with a list of choices.
     */
    Menu(ArrayList<String> choices) {
        this.choices = choices;
    }

    /**
     * Display a separator.
     */
    void displaySeparator() {
        System.out.println("");
    }

    /**
     * Display all choices.
     */
    void getChoices() {
        System.out.println("Please pick an option:");
        AtomicInteger choiceNumber = new AtomicInteger(1);
        for (String choice : this.choices) {
            System.out.println("(" + choiceNumber + ") " + choice);
            choiceNumber.getAndIncrement();
        }
    }

    /**
     * Return the number of available choices.
     *
     * @return the number of choices available for selection.
     */
    int getNumberOfChoices() {
        return this.choices.size();
    }

    /**
     * Return the choice number to exit from menu.
     *
     * @return the choice number to exit menu.
     */
    int getChoiceNumberToExit() {
        return this.choices.size();
    }

    /**
     * Running the menu until user choose the exit option and validate user choice.
     *
     * @param todos         A collection of <code>Task</code> object.
     * @param commandReader <code>Scanner</code> object to get user input.
     * @param menu          The menu that is currently running.
     */
    void menuLoop(TaskCollection todos, Scanner commandReader, Menu menu) {
        int commandToExit = getChoiceNumberToExit();
        int userChoice = 0;
        while (userChoice != commandToExit) {
            try {
                String input = commandReader.nextLine();
                userChoice = Integer.parseInt(input);
                if (userChoice <= 0 || userChoice > getNumberOfChoices()) {
                    throw new InvalidCommandException(userChoice);
                } else {
                    userChoice = menu.execute(userChoice, commandReader, todos);
                }
            } catch (NumberFormatException | InvalidCommandException error) {
                System.err.println("Invalid input. Please try again.");
            }
        }
    }

    /**
     * Display menu with options to edit task details and capture user input for running of operation.
     */
    public abstract void startup(Scanner commandReader, TaskCollection todos);

    /**
     * Display the available options.
     *
     * @param todos A collection of <code>Task</code> object.
     */
    abstract void displayMenu(TaskCollection todos);

    /**
     * Run selected option according to user input.
     *
     * @param userChoice    Input of user.
     * @param commandReader <code>Scanner</code> object to get user input.
     * @param todos         A collection of <code>Task</code> object.
     */
    abstract int executeMenuSwitcher(int userChoice, Scanner commandReader, TaskCollection todos);

    /**
     * {@inheritDoc}
     */
    @Override
    public int execute(int userChoice, Scanner commandReader, TaskCollection todos) {
        return executeMenuSwitcher(userChoice, commandReader, todos);
    }
}

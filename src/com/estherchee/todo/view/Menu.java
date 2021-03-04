package com.estherchee.todo.view;

import com.estherchee.todo.exception.InvalidCommandException;
import com.estherchee.todo.model.TaskCollection;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

abstract class Menu implements MenuSwitcher {
    final ArrayList<String> choices;

    Menu(ArrayList<String> choices) {
        this.choices = choices;
    }

    void displaySeparator() {
        System.out.println();
    }

    void getChoices() {
        System.out.println("Please pick an option:");
        AtomicInteger choiceNumber = new AtomicInteger(1);
        for (String choice : this.choices) {
            System.out.println("(" + choiceNumber + ") " + choice);
            choiceNumber.getAndIncrement();
        }
    }

    int getNumberOfChoices() {
        return this.choices.size();
    }

    int getChoiceNumberToExit() {
        return this.choices.size();
    }

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
                    userChoice = menu.execute(userChoice, commandToExit, commandReader, todos);
                }
            } catch (NumberFormatException | InvalidCommandException error) {
                String ANSI_RESET = "\u001B[0m";
                String ANSI_RED = "\u001B[31m";
                System.out.println(ANSI_RED + "Invalid input. Please try again." + ANSI_RESET);
            }
        }
    }

    public abstract void startup(Scanner commandReader, TaskCollection todos);

    abstract void getMenu(TaskCollection todos);

    abstract int executeMenuSwitcher(int userChoice, int commandToExit, Scanner commandReader, TaskCollection todos);

    @Override
    public int execute(int userChoice, int commandToExit, Scanner commandReader, TaskCollection todos) {
        return executeMenuSwitcher(userChoice, commandToExit, commandReader, todos);
    }
}

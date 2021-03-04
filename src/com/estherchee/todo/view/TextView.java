package com.estherchee.todo.view;

import com.estherchee.todo.exception.InvalidCommandException;

import java.util.Scanner;

public class TextView {
    private final MainMenu currentMenu;
    private final Scanner commandReader;
    private int userChoice;

    public TextView() {
        currentMenu = new MainMenu();
        commandReader = new Scanner(System.in);
        userChoice = 0;
    }

    public void startup() {
        currentMenu.getMainMenu();
        int COMMAND_TO_EXIT = 4;
        while (userChoice != COMMAND_TO_EXIT) {
            try {
                String input = commandReader.nextLine();
                this.userChoice = Integer.parseInt(input);
                if (this.userChoice <= 0 || this.userChoice > currentMenu.getNumberOfChoices()) {
                    throw new InvalidCommandException(userChoice);
                }
                System.out.println(input);
            } catch (NumberFormatException | InvalidCommandException error) {
                String ANSI_RESET = "\u001B[0m";
                String ANSI_RED = "\u001B[31m";
                System.out.println(ANSI_RED + "Invalid input. Please try again." + ANSI_RESET);
            }
        }
    }
}

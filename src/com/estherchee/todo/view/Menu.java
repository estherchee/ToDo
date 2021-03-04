package com.estherchee.todo.view;

import com.estherchee.todo.model.TaskCollection;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

abstract class Menu {
    final ArrayList<String> choices;

    Menu(ArrayList<String> choices) {
        this.choices = choices;
    }

    void showSeparator() {
        System.out.println("");
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

    abstract void getMenu(TaskCollection todos);

    public abstract void startup(Scanner commandReader, TaskCollection todos);
}

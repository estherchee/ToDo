package com.estherchee.todo.view;

import java.util.ArrayList;
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

    public int getNumberOfChoices() {
        return this.choices.size();
    }
}

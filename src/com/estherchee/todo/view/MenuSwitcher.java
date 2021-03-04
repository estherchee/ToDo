package com.estherchee.todo.view;

import com.estherchee.todo.model.TaskCollection;

import java.util.Scanner;

/**
 * The <code>MenuSwitcher</code> interface provide a method to execute a switching of operation based on user choice.
 */
public interface MenuSwitcher {
    /**
     * This method execute a switching of operation based on user choice.
     *
     * @param userChoice    Input of user.
     * @param commandReader <code>Scanner</code> object to get user input.
     * @param todos         A collection of <code>Task</code> object.
     */
    int execute(final int userChoice, final Scanner commandReader, final TaskCollection todos);
}

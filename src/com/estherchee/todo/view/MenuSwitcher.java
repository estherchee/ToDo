package com.estherchee.todo.view;

import com.estherchee.todo.model.TaskCollection;

import java.util.Scanner;

public interface MenuSwitcher {
    int execute(final int userChoice, final Scanner commandReader, final TaskCollection todos);
}

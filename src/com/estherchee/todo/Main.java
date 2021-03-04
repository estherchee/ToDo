package com.estherchee.todo;

import com.estherchee.todo.view.TextView;

/**
 * Initialized the Personify program.
 */
public class Main {
    /**
     * Construct {@link TextView} object and call method {@link TextView#startup()}
     * method to initialised the program
     *
     * @param args There are no command line expected.
     */
    public static void main(String[] args) {
        TextView app = new TextView();
        app.startup();
    }
}

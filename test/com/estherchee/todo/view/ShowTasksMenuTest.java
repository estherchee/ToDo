package com.estherchee.todo.view;

import com.estherchee.todo.model.TaskCollection;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowTasksMenuTest {
    static InputStream systemInBackup;
    Scanner commandReader;
    TaskCollection todos;
    ShowTasksMenu menu;

    @BeforeAll
    static void init() {
        systemInBackup = System.in;
    }

    @BeforeEach
    void setUp() {
        todos = new TaskCollection();
        todos.addTask("Test task 1", "2021-12-12", "Unit test");
        todos.addTask("Test task 2", "2021-08-08", "Unit test");
        todos.addTask("Test task 3", "2021-01-02", "Programming");
        menu = new ShowTasksMenu();
    }

    @Test
    void testDisplayTasksByDate() {
        String expected = "2021-01-02";
        String command = "1\n" + "\n";
        InputStream input = new ByteArrayInputStream(command.getBytes());
        System.setIn(input);

        commandReader = new Scanner(System.in);
        menu.startup(commandReader, todos);
        String actual = todos.getTasks().get(0).getDueDate();

        assertEquals(expected, actual);
    }

    @Test
    void testDisplayTasksByProjectName() {
        String expected = "Programming";
        String command = "2\n" + "\n";
        InputStream input = new ByteArrayInputStream(command.getBytes());
        System.setIn(input);

        commandReader = new Scanner(System.in);
        menu.startup(commandReader, todos);
        String actual = todos.getTasks().get(0).getProjectName();

        assertEquals(expected, actual);
    }

    @AfterAll
    static void tearDown() {
        System.setIn(systemInBackup);
    }
}

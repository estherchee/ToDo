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

public class MainMenuTest {
    static InputStream systemInBackup;
    Scanner commandReader;
    TaskCollection todos;
    MainMenu menu;

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
        menu = new MainMenu();
    }

    @Test
    void testShowTaskMenuSortByDate() {
        String expected = "2021-01-02";
        String command = "1\n" + "1\n" + "\n" + "4\n";
        InputStream input = new ByteArrayInputStream(command.getBytes());
        System.setIn(input);

        commandReader = new Scanner(System.in);
        menu.startup(commandReader, todos);
        String actual = todos.getTasks().get(0).getDueDate();

        assertEquals(expected, actual);
    }

    @Test
    void testAddTask() {
        String expected = "2020-12-20";
        String command = "2\n" + "New Task Title\n" + expected + "\n" + "programming\n" + "\n" + "4\n";
        InputStream input = new ByteArrayInputStream(command.getBytes());
        System.setIn(input);

        commandReader = new Scanner(System.in);
        menu.startup(commandReader, todos);
        String actual = todos.getTasks().get(3).getDueDate();

        assertEquals(expected, actual);
    }

    @Test
    void testEditTaskUpdateDueDate() {
        String expected = "2021-12-10";
        String command = "3\n" + "2\n" + "1\n" + expected + "\n" + "\n"+"4\n";
        InputStream input = new ByteArrayInputStream(command.getBytes());
        System.setIn(input);

        commandReader = new Scanner(System.in);
        menu.startup(commandReader, todos);
        String actual = todos.getTasks().get(0).getDueDate();

        assertEquals(expected, actual);
    }


    @AfterAll
    static void tearDown() {
        System.setIn(systemInBackup);
    }
}

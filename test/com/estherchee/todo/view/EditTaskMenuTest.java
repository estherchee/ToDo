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

public class EditTaskMenuTest {
    static InputStream systemInBackup;
    Scanner commandReader;
    TaskCollection todos;
    EditTaskMenu menu;

    @BeforeAll
    static void init() {
        systemInBackup = System.in;
    }

    @BeforeEach
    void setUp() {
        todos = new TaskCollection();
        todos.addTask("Test task", "2021-12-12", "Unit test");
        menu = new EditTaskMenu();
    }

    @Test
    void testUpdateTaskTitle() {
        String expected = "New task details";
        String command = "1\n" + "1\n" + expected + "\n" + "\n";
        InputStream input = new ByteArrayInputStream(command.getBytes());
        System.setIn(input);

        commandReader = new Scanner(System.in);
        menu.startup(commandReader, todos);
        String actual = todos.getTasks().get(0).getTitle();

        assertEquals(expected, actual);
    }

    @Test
    void testUpdateDueDate(){
        String expected = "2021-12-10";
        String command = "2\n" + "1\n" + expected + "\n" + "\n";
        InputStream input = new ByteArrayInputStream(command.getBytes());
        System.setIn(input);

        commandReader = new Scanner(System.in);
        menu.startup(commandReader, todos);
        String actual = todos.getTasks().get(0).getDueDate();

        assertEquals(expected, actual);
    }

    @Test
    void testUpdateProjectName(){
        String expected = "New project name";
        String command = "3\n" + "1\n" + expected + "\n" + "\n";
        InputStream input = new ByteArrayInputStream(command.getBytes());
        System.setIn(input);

        commandReader = new Scanner(System.in);
        menu.startup(commandReader, todos);
        String actual = todos.getTasks().get(0).getProjectName();

        assertEquals(expected, actual);
    }

    @Test
    void testMarkTaskAsDone(){
        String command = "4\n" + "1\n" + "\n";
        InputStream input = new ByteArrayInputStream(command.getBytes());
        System.setIn(input);

        commandReader = new Scanner(System.in);
        menu.startup(commandReader, todos);
        Boolean actual = todos.getTasks().get(0).getTaskStatus();

        assertEquals(true, actual);
    }

    @Test
    void testRemoveTask(){
        String command = "5\n" + "1\n" + "1\n" + "\n";
        InputStream input = new ByteArrayInputStream(command.getBytes());
        System.setIn(input);

        commandReader = new Scanner(System.in);
        menu.startup(commandReader, todos);
        int actual = todos.getTasks().size();

        assertEquals(0, actual);
    }

    @AfterAll
    static void tearDown() {
        System.setIn(systemInBackup);
    }
}

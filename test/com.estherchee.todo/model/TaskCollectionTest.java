package com.estherchee.todo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskCollectionTest {
    TaskCollection tasks;

    @BeforeEach
    void setUp() {
        LocalDate currentDate = LocalDate.now();
        tasks = new TaskCollection();
        tasks.addTask("Title1", currentDate.toString(), "Z");
        tasks.addTask("Title2", currentDate.minusDays(100).toString(), "A");
    }

    @Test
    void testAddTaskWithStatusIntoCollection() {
        tasks.addTask("Title", LocalDate.now().toString(), "Project name");

        String expectedResult = "Title";
        String result = tasks.getTasks().get(2).getTitle();

        assertEquals(expectedResult, result);
    }

    @Test
    void testRemoveTaskFromCollection() {
        tasks.removeTask(0);

        int expectedResult = 1;
        int result = tasks.getTasks().size();

        assertEquals(expectedResult, result);
    }

    @Test
    void testUpdateTaskTitle() {
        String expectedResult = "New title";

        tasks.updateTaskTitle(0, expectedResult);
        String result = tasks.getTasks().get(0).getTitle();

        assertEquals(expectedResult, result);
    }

    @Test
    void testUpdateDueDate() {
        String expectedResult = LocalDate.now().plusDays(10).toString();

        tasks.updateDueDate(0, expectedResult);
        String result = tasks.getTasks().get(0).getDueDate();

        assertEquals(expectedResult, result);
    }

    @Test
    void testMarkTaskAsCompletedInCollection() {
        tasks.markAsCompleted(0);
        boolean result = tasks.getTasks().get(0).getTaskStatus();

        assertTrue(result);
    }

    @Test
    void testUpdateProjectName() {
        String expectedResult = "New project name";

        tasks.updateProjectName(0, expectedResult);
        String result = tasks.getTasks().get(0).getProjectName();

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetTasksAccordingToDueDate() {
        String expectedResult = "A";

        String result = tasks.getTasksByDate().get(0).getProjectName();

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetTasksAccordingToProjectName() {
        String expectedResult = "A";

        String result = tasks.getTasksByProject().get(0).getProjectName();

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetNumberOfCompletedTask() {
        int expectedResult = 0;

        int result = tasks.getNumberOfCompletedTask();

        assertEquals(expectedResult, result);
    }

    @Test
    void testGetNumberOfTaskTodo() {
        int expectedResult = 2;

        int result = tasks.getNumberOfInProgressTask();

        assertEquals(expectedResult, result);
    }
}

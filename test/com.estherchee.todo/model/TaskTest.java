package com.estherchee.todo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private String title;
    private LocalDate dueDate;
    private String projectName;
    private Task task;

    @BeforeEach
    void setUp() {
        title = "Doing unit testing";
        dueDate = LocalDate.now();
        projectName = "Unit test";
        task = new Task(title, dueDate, projectName);
    }

    @Test
    void testTaskInitialisedWithoutGivenStatus() {
        boolean result = task.getTaskStatus();

        assertFalse(result);
    }

    @Test
    void testTaskInitialisedWithIsCompletedStatus() {
        task = new Task(title, dueDate, true, projectName);
        boolean result = task.getTaskStatus();

        assertTrue(result);
    }

    @Test
    void testUpdateTitleOfTask() {
        String expectedTitle = "New test title";

        task.updateTitle(expectedTitle);
        String result = task.getTitle();

        assertEquals(expectedTitle, result);
    }

    @Test
    void testUpdateDueDate() {
        LocalDate expectedDueDate = LocalDate.now().plusDays(3);

        task.updateDueDate(expectedDueDate.toString());
        String result = task.getDueDate();

        assertEquals(expectedDueDate.toString(), result);
    }

    @Test
    void testMarkTaskAsCompleted() {
        task.markTaskAsCompleted();
        boolean result = task.getTaskStatus();

        assertTrue(result);
    }

    @Test
    void testUpdateProjectName() {
        String expectedProjectName = "Update project name";

        task.updateProjectName(expectedProjectName);
        String result = task.getProjectName();

        assertEquals(expectedProjectName, result);
    }

    @Test
    void testDueDateComparator() {
        String title1 = "Task1";
        LocalDate dueDate1 = LocalDate.now();
        Task task1 = new Task(title1, dueDate1, "");
        String title2 = "Task2";
        LocalDate dueDate2 = LocalDate.now().minusDays(10);
        Task task2 = new Task(title2, dueDate2, "");
        List<Task> tasks = new ArrayList<>(Arrays.asList(task1, task2));

        tasks.sort(Task.dueDateComparator);
        String expectedResult = task2.getTitle();
        String result = tasks.get(0).getTitle();

        assertEquals(expectedResult, result);
    }

    @Test
    void testProjectNameComparator() {
        String title1 = "Task1";
        LocalDate dueDate1 = LocalDate.now();
        Task task1 = new Task(title1, dueDate1, "A");
        String title2 = "Task2";
        LocalDate dueDate2 = LocalDate.now().minusDays(10);
        Task task2 = new Task(title2, dueDate2, "Z");
        List<Task> tasks = new ArrayList<>(Arrays.asList(task1, task2));

        tasks.sort(Task.projectNameComparator);
        String expectedResult = task1.getTitle();
        String result = tasks.get(0).getTitle();

        assertEquals(expectedResult, result);
    }
}

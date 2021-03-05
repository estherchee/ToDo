package com.estherchee.todo.repository;

import com.estherchee.todo.model.TaskCollection;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskIOTest {
    private TaskIO taskIO;
    private static final Path ORIGINAL_PATH = Paths.get("src/data/tasks.csv");
    private static final Path COPY_PATH = Paths.get("src/data/tasks_backup_for_test.csv");

    @BeforeAll
    static void setUpBeforeAll() throws IOException {
        Files.deleteIfExists(COPY_PATH);
        Files.copy(ORIGINAL_PATH, COPY_PATH);
    }

    @AfterAll
    static void tearDownAfterAll() throws IOException {
        Files.deleteIfExists(ORIGINAL_PATH);
        Files.copy(COPY_PATH, ORIGINAL_PATH);
    }

    @BeforeEach
    void setUp() {
        taskIO = new TaskIO();
        TaskCollection tasks = new TaskCollection();
        tasks.addTask("Task", LocalDate.now().toString(), "Project");
        taskIO.writeTasksToFile(tasks);
    }

    @Test
    void testReadTaskDataFromFile() {
        String expectedResult = "Task";

        TaskCollection tasks = taskIO.readTasksDataFromDataFile();
        String result = tasks.getTasks().get(0).getTitle();

        assertEquals(expectedResult, result);
    }

    @Test
    void testReadTasksDataWithoutDataFile() throws IOException {
        Files.deleteIfExists(ORIGINAL_PATH);

        TaskCollection tasks = taskIO.readTasksDataFromDataFile();
        int expectedSize = 0;
        int actualSize = tasks.getTasks().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void writeTasksToFile() {
        TaskCollection tasks = new TaskCollection();
        tasks.addTask("Task1", LocalDate.now().toString(), "Project");
        tasks.addTask("Task2", LocalDate.now().toString(), "Project");

        taskIO.writeTasksToFile(tasks);
        tasks = taskIO.readTasksDataFromDataFile();
        int expectedSize = 2;
        int result = tasks.getTasks().size();

        assertEquals(expectedSize, result);
    }
}

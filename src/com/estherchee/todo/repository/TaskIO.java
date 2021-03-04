package com.estherchee.todo.repository;

import com.estherchee.todo.model.Task;
import com.estherchee.todo.model.TaskCollection;
import com.estherchee.todo.view.TextView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


/**
 * Provides methods to read collection of <code>Task</code> object from file and write to file.
 */
public class TaskIO extends FileIO {
    private final String filePath;
    private final Path path;

    /**
     * Construct {@link TaskIO} object for read and saving of <code>Task</code>.
     */
    public TaskIO() {
        filePath = "src/data/tasks.csv";
        path = Paths.get(filePath).toAbsolutePath();
    }

    private boolean isFileExistsAndReadable() {
        return Files.isReadable(path);
    }

    private List<String> tokenizeTasksDetails(List<String> tasks) {
        List<String> tasksInfo = new ArrayList<>();
        for (String task : tasks) {
            Scanner scanner = new Scanner(task);
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                tasksInfo.add(scanner.next());
            }
            scanner.close();
        }
        return tasksInfo;
    }

    private TaskCollection convertIntoTaskCollection(List<String> tokenizeTasks) {
        TaskCollection tasks = new TaskCollection();

        Iterator<String> it = tokenizeTasks.iterator();
        while (it.hasNext()) {
            String title = it.next();
            String dueDate = it.next();
            String isCompleted = it.next();
            String projectName = it.next();
            tasks.addTask(title, dueDate, isCompleted.equals("true"), projectName);
        }
        return tasks;
    }

    private TaskCollection readTaskIntoTaskCollection() {
        List<String> tasks = readEachLineOfFile(path);
        List<String> tokenizeTasks = tokenizeTasksDetails(tasks);
        return convertIntoTaskCollection(tokenizeTasks);
    }

    /**
     * Read tasks data from of a user to system.
     *
     * @return a <code>TaskCollection</code> object.
     */
    public TaskCollection readTasksDataFromDataFile() {
        TaskCollection tasks = new TaskCollection();
        if (isFileExistsAndReadable()) {
            tasks = readTaskIntoTaskCollection();
        }
        return tasks;
    }

    private List<String> formatTasksForSaving(List<Task> tasks) {
        List<String> dataToWriteToFile = tasks.stream()
                .map(task -> String.format("%s;%s;%s;%s;",
                        task.getTitle(),
                        task.getDueDate(),
                        task.getTaskStatus(),
                        task.getProjectName())).collect(Collectors.toList());
        return dataToWriteToFile;
    }

    private void writeToFile(final List<String> tasksToWriteToFile) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            System.err.println("IO error while manipulating file.");
        }
        final Path newFile = Paths.get(filePath);
        FileIO writeFile = new FileIO();
        writeFile.writeTaskToFile(newFile, tasksToWriteToFile);
    }

    /**
     * Write tasks data to file.
     *
     * @param tasks <code>TaskCollection</code> object.
     */
    public void writeTasksToFile(final TaskCollection tasks) {
        List<String> tasksToWriteToFile = formatTasksForSaving(tasks.getTasks());
        writeToFile(tasksToWriteToFile);
    }
}

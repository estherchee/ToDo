package com.estherchee.todo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskCollection provides an object for managing a list tasks.
 */
public class TaskCollection {
    private final List<Task> tasks;

    /**
     * Instantiate a <code>TaskCollection</code> object.
     */
    public TaskCollection() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add a <code>Task</code> object with given information into collection including status.
     *
     * @param title       Title of a task.
     * @param dueDate     Due date of a task as <code>LocalDate</code>.
     * @param isCompleted Status of a task
     * @param projectName Type of the task.
     */
    public void addTask(final String title, final String dueDate, boolean isCompleted, final String projectName) {
        Task task = new Task(title, LocalDate.parse(dueDate), isCompleted, projectName);
        this.tasks.add(task);
    }

    /**
     * Add a <code>Task</code> object with given information into collection.
     *
     * @param title       Title of a task.
     * @param dueDate     Due date of a task as <code>LocalDate</code>.
     * @param projectName Type of the task.
     */
    public void addTask(final String title, final String dueDate, final String projectName) {
        Task task = new Task(title, LocalDate.parse(dueDate), projectName);
        this.tasks.add(task);
    }

    /**
     * Remove a <code>Task</code> object from collection with given index.
     *
     * @param index Index of the <code>Task</code> object to remove.
     */
    public void removeTask(final int index) {
        this.tasks.remove(index);
    }

    /**
     * Update the title of a <code>Task</code> object from collection with given index.
     *
     * @param index    Index of the <code>Task</code> object to update.
     * @param newTitle New title of the <code>Task</code> object.
     */
    public void updateTaskTitle(final int index, final String newTitle) {
        this.tasks.get(index).updateTitle(newTitle);
    }

    /**
     * Update the due date of a <code>Task</code> object from collection with given index.
     *
     * @param index   Index of the <code>Task</code> object to update.
     * @param newDate New due date of the <code>Task</code> object.
     */
    public void updateDueDate(final int index, final String newDate) {
        this.tasks.get(index).updateDueDate(newDate);
    }

    /**
     * Update the status of a <code>Task</code> object to completed from collection with given index.
     *
     * @param index Index of the <code>Task</code> object to update.
     */
    public void markAsCompleted(final int index) {
        this.tasks.get(index).markTaskAsCompleted();
    }

    /**
     * Update the project name of a <code>Task</code> object from collection with given index.
     *
     * @param index          Index of the <code>Task</code> object to update.
     * @param newProjectName New project name of the <code>Task</code> object.
     */
    public void updateProjectName(final int index, final String newProjectName) {
        this.tasks.get(index).updateProjectName(newProjectName);
    }

    /**
     * Get the list of <code>Task</code> object.
     *
     * @return a list of <code>Task</code> object.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Get a list of <code>Task</code> object based on object's due date. Object with closest due date will be display
     * first.
     *
     * @return a list of <code>Task</code> object.
     */
    public List<Task> getTasksByDate() {
        this.tasks.sort(Task.dueDateComparator);
        return this.tasks;
    }

    /**
     * Get a list of <code>Task</code> object based on alphabetical order.
     *
     * @return a list of <code>Task</code> object.
     */
    public List<Task> getTasksByProject() {
        this.tasks.sort(Task.projectNameComparator);
        return this.tasks;
    }

    /**
     * Get a the number of <code>Task</code> that have been completed.
     *
     * @return a number represent the number of tasks.
     */
    public int getNumberOfCompletedTask() {
        return (int) this.tasks.stream()
                .filter(Task::getTaskStatus).count();
    }

    /**
     * Get a the number of <code>Task</code> that are in progress.
     *
     * @return a number represent the number of tasks.
     */
    public int getNumberOfInProgressTask() {
        return this.tasks.size() - this.getNumberOfCompletedTask();
    }
}

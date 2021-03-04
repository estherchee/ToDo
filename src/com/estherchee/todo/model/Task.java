package com.estherchee.todo.model;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Task provides an object for managing a task. Each task object will hold information about the task name, due date,
 * status and type of a task.
 */
public class Task {
    private String title;
    private LocalDate dueDate;
    private boolean isCompleted;
    private String projectName;

    /**
     * Instantiate a <code>Task</code> object with provided information.
     *
     * @param title       Title of a task.
     * @param dueDate     Due date of a task as <code>LocalDate</code>.
     * @param projectName Type of the task.
     */
    Task(final String title, final LocalDate dueDate, final String projectName) {
        this.title = title;
        this.dueDate = dueDate;
        this.isCompleted = false;
        this.projectName = projectName;
    }

    /**
     * Instantiate a task object with provided information including status.
     *
     * @param title       Title of a task.
     * @param dueDate     Due date of a task as <code>LocalDate</code>.
     * @param isCompleted Status of a task
     * @param projectName Type of the task.
     */
    Task(final String title, final LocalDate dueDate, final boolean isCompleted, final String projectName) {
        this(title, dueDate, projectName);
        this.isCompleted = isCompleted;
    }

    /**
     * Get title of object.
     *
     * @return The title of object.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Modify title of object.
     *
     * @param newTitle Element for modification of object's title.
     */
    void updateTitle(final String newTitle) {
        this.title = newTitle;
    }

    /**
     * Get due date of object.
     *
     * @return The due date of object as <code>String</code>.
     */
    public String getDueDate() {
        return dueDate.toString();
    }

    /**
     * Modify due date of object.
     *
     * @param newDueDate Element for modification of object's due date.
     */
    void updateDueDate(final String newDueDate) {
        this.dueDate = LocalDate.parse(newDueDate);
    }

    /**
     * Get status of object.
     *
     * @return true if the task is completed.
     */
    public boolean getTaskStatus() {
        return this.isCompleted;
    }

    /**
     * Mark status of object as completed.
     */
    void markTaskAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * Get project name of object.
     *
     * @return project name of object.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Modify project name of object.
     *
     * @param newProjectName Element for modification of object's project name.
     */
    void updateProjectName(final String newProjectName) {
        this.projectName = newProjectName;
    }

    /**
     * Compare given <code>Task</code> objects based on due date.
     *
     * @param task1 <code>Task</code> object to compare.
     * @param task2 <code>Task</code> object to compare.
     * @return a <code>Comparator</code> object.
     */
    static Comparator<Task> dueDateComparator = (task1, task2) -> {
        if (task1.dueDate.isBefore(task2.dueDate)) {
            return -1;
        } else if (task1.dueDate.isEqual(task2.dueDate)) {
            return 0;
        } else {
            return 1;
        }
    };

    /**
     * Compare given <code>Task</code> objects based on project name.
     *
     * @param task1 <code>Task</code> object to compare.
     * @param task2 <code>Task</code> object to compare.
     * @return a <code>Comparator</code> object.
     */
    static Comparator<Task> projectNameComparator = Comparator.comparing(task -> task.projectName);

    private String capitalisedFirstLetterOfString(final String originalString) {
        return originalString.substring(0, 1).toUpperCase() + originalString.substring(1);
    }

    @Override
    public String toString() {
        String status;
        if (this.isCompleted) {
            status = "Completed";
        } else {
            status = "In progress";
        }
        String line1 = "Title        : " + capitalisedFirstLetterOfString(this.title);
        String line2 = "Due date     : " + this.dueDate.toString();
        String line3 = "Status       : " + status;
        String line4 = "Project name : " + capitalisedFirstLetterOfString(this.projectName);
        return line1 + "\n" + line2 + "\n" + line3 + "\n" + line4;
    }
}

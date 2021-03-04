package com.estherchee.todo.models;

import java.time.LocalDate;
import java.util.Comparator;

/**
 * Task provides an object for managing a task. Each task object will hold information about the task name, due date,
 * status and type of a task.
 */
class Task {
    private String title;
    private LocalDate dueDate;
    private Boolean isCompleted;
    private String project;

    /**
     * Instantiate a task object with provided information.
     *
     * @param title   Title of a task.
     * @param dueDate Due date of a task.
     * @param project Type of the task.
     */
    Task(final String title, final LocalDate dueDate, final String project) {
        this.title = title;
        this.dueDate = dueDate;
        this.isCompleted = false;
        this.project = project;
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
     * Modify due date of object.
     *
     * @param newDueDate Element for modification of object's due date.
     */
    void updateDueDate(final String newDueDate) {
        this.dueDate = LocalDate.parse(newDueDate);
    }

    /**
     * Mark status of object as completed.
     */
    void markTaskAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * Modify type of object.
     *
     * @param newProject Element for modification of object's project.
     */
    void updateProject(final String newProject) {
        this.project = newProject;
    }

    static Comparator<Task> dueDateComparator = (task1, task2) -> {
        if (task1.dueDate.isBefore(task2.dueDate)) {
            return -1;
        } else if (task1.dueDate.isEqual(task2.dueDate)) {
            return 0;
        } else {
            return 1;
        }
    };

    static Comparator<Task> projectComparator = Comparator.comparing(task -> task.project);

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
        String line1 = "Title    : " + capitalisedFirstLetterOfString(this.title);
        String line2 = "Due date : " + this.dueDate.toString();
        String line3 = "Status   : " + status;
        String line4 = "Project  : " + capitalisedFirstLetterOfString(this.project);
        return line1 + "\n" + line2 + "\n" + line3 + "\n" + line4;
    }
}

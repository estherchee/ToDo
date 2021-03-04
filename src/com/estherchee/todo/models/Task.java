package com.estherchee.todo.models;

/**
 * Task provides an object for managing a task. Each task object will hold information about the task name, due date,
 * status and type of a task.
 */
public class Task {
    private String title;
    private String dueDate;
    private String status;
    private String project;

    /**
     * Instantiate a task object with provided information.
     *
     * @param title     Title of a task.
     * @param dueDate   Due date of a task.
     * @param status    Status of the task.
     * @param project   Type of the task.
     */
    public Task(final String title, final String dueDate, final String status, final String project) {
        this.title = title;
        this.dueDate = dueDate;
        this.status = status;
        this.project = project;
    }

    private String capitalisedFirstLetterOfString(final String originalString) {
        return originalString.substring(0, 1).toUpperCase() + originalString.substring(1);
    }

    @Override
    public String toString() {
        String line1 = "Title    : " + capitalisedFirstLetterOfString(this.title);
        String line2 = "Due date : " + this.dueDate;
        String line3 = "Status   : " + capitalisedFirstLetterOfString(this.status);
        String line4 = "Project  : " + capitalisedFirstLetterOfString(this.project);
        return line1 + "\n" + line2 + "\n" + line3 + "\n" + line4;
    }
}

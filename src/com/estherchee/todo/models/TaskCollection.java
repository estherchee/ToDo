package com.estherchee.todo.models;

import java.util.ArrayList;
import java.util.List;

public class TaskCollection {
    private List<Task> tasks;

    public TaskCollection() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(final String title, final String dueDate, final String project) {
        Task task = new Task(title, dueDate, project);
        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
}

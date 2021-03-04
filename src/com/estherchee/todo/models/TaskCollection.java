package com.estherchee.todo.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskCollection {
    private List<Task> tasks;

    public TaskCollection() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(final String title, final String dueDate, final String project) {
        Task task = new Task(title, LocalDate.parse(dueDate), project);
        this.tasks.add(task);
    }

    public List<Task> getTasksByDate() {
        tasks.sort(Task.dueDateComparator);
        return this.tasks;
    }

    public List<Task> getTasksByProject() {
        tasks.sort(Task.projectComparator);
        return this.tasks;
    }
}

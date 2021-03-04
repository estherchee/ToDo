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

    public void removeTask(final int index) {
        this.tasks.remove(index);
    }

    public void updateTaskTitle(final int index, final String newTitle) {
        this.tasks.get(index).updateTitle(newTitle);
    }

    public void updateDueDate(final int index, final String newDate) {
        this.tasks.get(index).updateDueDate(newDate);
    }

    public void markAsCompleted(final int index) {
        this.tasks.get(index).markTaskAsCompleted();
    }

    public void updateProjectName(final int index, final String newProjectName) {
        this.tasks.get(index).updateProjectName(newProjectName);
    }

    public List<Task> getTasksByDate() {
        tasks.sort(Task.dueDateComparator);
        return this.tasks;
    }

    public List<Task> getTasksByProject() {
        tasks.sort(Task.projectNameComparator);
        return this.tasks;
    }
}

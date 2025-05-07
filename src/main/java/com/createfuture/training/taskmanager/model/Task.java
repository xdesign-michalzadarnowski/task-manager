package com.createfuture.training.taskmanager.model;

public class Task {
    private String title;


    public Task() {
        // Default constructor is needed for Spring's data binding
    }

    public Task(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                '}';
    }
}

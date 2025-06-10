package com.createfuture.training.taskmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean done;

    public Task() {
        // Default constructor is needed for Spring's data binding
    }

    public Task(String title) {
        this.title = title;
        this.done = false; 
    }

    public Task(long id, String title) {
        this.id = id;
        this.title = title;
        this.done = false; 
    }

    public Task(long id, String title, boolean done) {
        this.id = id;
        this.title = title;
        this.done = done; 
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", done=" + done +
                '}';
    }
}

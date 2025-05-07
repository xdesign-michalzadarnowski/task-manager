package com.createfuture.training.taskmanager.service;

import com.createfuture.training.taskmanager.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final Deque<Task> taskQueue = new ArrayDeque<>();

    public void addTask(Task task) {
        taskQueue.offer(task);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(taskQueue);
    }

    public List<Task> getTopNTasks(int n) {
        return taskQueue.stream().limit(n).collect(Collectors.toList());
    }

    public boolean markDone(String title) {
        return taskQueue.removeIf(task -> task.getTitle().equals(title));
    }

    public void clearTasks() {
        taskQueue.clear();
    }
}

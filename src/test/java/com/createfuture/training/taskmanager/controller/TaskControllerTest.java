package com.createfuture.training.taskmanager.controller;

import com.createfuture.training.taskmanager.model.Task;
import com.createfuture.training.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskControllerTest {

    private final TaskService taskService;

    @Autowired
    public TaskControllerTest(TaskService taskService) {
        this.taskService = taskService;
    }

    // POST /api/tasks
    @PostMapping
    public void addTask(@RequestBody Task task) {
        taskService.addTask(task);
    }

    // GET /api/tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // GET /api/tasks/top?n=3
    @GetMapping("/top")
    public List<Task> getTopNTasks(@RequestParam(defaultValue = "3") int n) {
        return taskService.getTopNTasks(n);
    }

    // DELETE /api/tasks?title=TaskTitle
    @DeleteMapping
    public boolean markTaskDone(@RequestParam String title) {
        return taskService.markDone(title);
    }
}

package com.createfuture.training.taskmanager.service;

import com.createfuture.training.taskmanager.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void addTask_ShouldAddTaskToQueue() {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        taskService.addTask(task1);
        taskService.addTask(task2);
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size());
        assertTrue(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
    }

    @Test
    void getAllTasks_ShouldReturnAllTasks() {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        taskService.addTask(task1);
        taskService.addTask(task2);
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Task 2", tasks.get(1).getTitle());
    }

    @Test
    void getTopNTasks_ShouldReturnTopNTasks() {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        Task task3 = new Task("Task 3");
        taskService.addTask(task1);
        taskService.addTask(task2);
        taskService.addTask(task3);
        List<Task> top2Tasks = taskService.getTopNTasks(2);
        assertEquals(2, top2Tasks.size());
        assertEquals("Task 1", top2Tasks.get(0).getTitle());
        assertEquals("Task 2", top2Tasks.get(1).getTitle());
    }

    @Test
    void getTopNTasks_ShouldReturnEmptyListWhenNIsZero() {
        List<Task> top0Tasks = taskService.getTopNTasks(0);
        assertEquals(0, top0Tasks.size());
    }

    @Test
    void getTopNTasks_ShouldReturnAllTasksWhenNIsGreaterThanSize() {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        taskService.addTask(task1);
        taskService.addTask(task2);
        List<Task> allTasks = taskService.getTopNTasks(3);
        assertEquals(2, allTasks.size());
        assertEquals("Task 1", allTasks.get(0).getTitle());
        assertEquals("Task 2", allTasks.get(1).getTitle());
    }

    @Test
    void markDone_ShouldRemoveTask() {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        taskService.addTask(task1);
        taskService.addTask(task2);
        boolean result = taskService.markDone("Task 1");
        assertTrue(result);
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(1, tasks.size());
        assertFalse(tasks.contains(task1));
        assertTrue(tasks.contains(task2));
    }

    @Test
    void markDone_ShouldReturnFalseIfTaskNotFound() {
        Task task1 = new Task("Task 1");
        taskService.addTask(task1);
        boolean result = taskService.markDone("Nonexistent Task");
        assertFalse(result);
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(1, tasks.size());
        assertTrue(tasks.contains(task1));
    }
}

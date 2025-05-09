package com.createfuture.training.taskmanager.repository;

import com.createfuture.training.taskmanager.model.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Task> taskMapper = (rs, rowNum) -> new Task(rs.getLong("id"), rs.getString("title"));

    public void addTask(String title) {
        String sql = "INSERT INTO tasks (title) VALUES ('" + title + "')";
        jdbcTemplate.execute(sql);
    }

    public List<Task> findAll() {
        return jdbcTemplate.query("SELECT * FROM tasks", taskMapper);
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM tasks WHERE id = ?", id);
    }

    public List<Task> findTopN(int n) {
        String sql = "SELECT * FROM tasks LIMIT ?";
        return jdbcTemplate.query(sql, taskMapper, n);
    }

    public void reset() {
        jdbcTemplate.execute("DELETE FROM tasks");
    }
}

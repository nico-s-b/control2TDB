package com.example.control2.repositories;

import com.example.control2.models.Task;

import java.util.List;

public interface TaskRepository {
    Task findById(Long id);
    List<Task> findByUser(Long userId);
    Task save(Task task);
    Task update(Task task);
    Task delete(Task task);
    List<Task> findByState(Long userId, Boolean state);
    List<Task> findByKeyword(Long userId, String keyword);
    Task changeState(Task task);
    Task findByFinishingDeadline(Long userId);
}

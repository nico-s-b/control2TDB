package com.example.control2.repositories;

import com.example.control2.models.Task;

import java.util.List;

public interface TaskRepository {
    Task findById(Long id);
    List<Task> findByUser(Long userId);
    void save(Task task);
    void delete(Task task);
    void findByState(Long userId, Boolean state);
    void findByKeyword(Long userId, String keyword);
}

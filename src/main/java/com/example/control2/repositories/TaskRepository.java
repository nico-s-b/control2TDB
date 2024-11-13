package com.example.control2.repositories;

import com.example.control2.models.Task;

import java.util.List;

public interface TaskRepository {
    Task findById(Long id);
    List<Task> findByUser(Long userId);
    Task save(Task task);
    Task update(Task task);
    Task delete(Task task);
    //void findByState(Long userId, Boolean state);
    //void findByKeyword(Long userId, String keyword);
    Task changeState(Task task);
}

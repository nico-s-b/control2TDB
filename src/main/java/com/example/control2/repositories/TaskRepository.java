package com.example.control2.repositories;

import com.example.control2.models.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository {
    Task findById(Long id);
    List<Task> findByUser(Long userid);
    Task save(Task task);
    Task update(Task task);
    void delete(Task task);
    List<Task> findByState(Long userId, Boolean state);
    List<Task> findByKeyword(Long userId, String keyword);
    Task changeState(Task task);
    List<Task> findByFinishingDeadline(Long userId, int hours);
}

package com.example.control2.repositories;

import com.example.control2.models.Task;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface TaskRepository {
    Task findById(Long id);
    List<Task> findByUser(Long userId);
    Task save(Task task);
    Task update(Task task);
    Task delete(Task task);
    List<Task> findByState(Long userId, Boolean state);
    List<Task> findByKeyword(Long userId, String keyword);
    Task changeState(Task task);
    List<Task> findByFinishingDeadline(Long userId, int hours);
}

package com.example.control2.repositories;

import com.example.control2.models.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    @Override
    public Task findById(Long id) {
        return null;
    }

    @Override
    public List<Task> findByUser(Long userId) {
        return List.of();
    }

    @Override
    public void save(Task task) {

    }

    @Override
    public void update(Task task) {

    }

    @Override
    public void delete(Task task) {

    }

    @Override
    public void findByState(Long userId, Boolean state) {

    }

    @Override
    public void findByKeyword(Long userId, String keyword) {

    }
}

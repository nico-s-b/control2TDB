package com.example.control2.services;

import com.example.control2.models.Task;
import com.example.control2.repositories.TaskRepositoryImpl;
import com.example.control2.repositories.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepositoryImpl taskRepository;
    @Autowired
    UserRepositoryImpl userRepository;

    public Task getTaskById(Long id) {
        return null;
    }
    public Task saveTask(Task task, Long userId) {
        return null;
    }
    public void deleteTask(Long id) {}
    public Task updateTask(Task task) {
        return null;
    }

    public List<Task> getTasksByUser(Long userId) {
        return null;
    }
    public Task changeTaskState(Long id){
        return null;
    }

    public List<Task> filterTaskByState(Long userId, Boolean state) {
        return null;
    }
    public List<Task> searchTaskByKeyword(Long userId, String keyword) {
        return null;
    }
    public void sendNotification(Long userId) {}
}

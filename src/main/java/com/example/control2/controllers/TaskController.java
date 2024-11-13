package com.example.control2.controllers;

import com.example.control2.models.Task;
import com.example.control2.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.saveTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUser(userId);
        if (tasks == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Task> deleteTask(@RequestBody Task task) {
        Task deletedTask = taskService.deleteTask(task);
        if (deletedTask == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedTask);
    }

    @PutMapping("/state")
    public ResponseEntity<Task> changeTaskState(@RequestBody Task task) {
        Task updatedtask = taskService.changeTaskState(task);
        if (updatedtask == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedtask);
    }
    
}

package com.example.control2.controllers;

import com.example.control2.models.Task;
import com.example.control2.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.ok(tasks);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTask(@RequestBody Task task) {
        try{
            taskService.deleteTask(task);
            return ResponseEntity.ok("Tarea eliminada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la tarea");
        }

    }

    @GetMapping("/{userId}/tasks/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable Long userId, @RequestParam Boolean status) {
        List<Task> tasks = taskService.filterTaskByState(userId, status);
        if (tasks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{userId}/tasks/keyword")
    public ResponseEntity<List<Task>> getTasksByKeyword(@PathVariable Long userId, @RequestParam String keyword) {
        List<Task> tasks = taskService.searchTaskByKeyword(userId, keyword);
        if (tasks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{userId}/tasks/notify")
    public ResponseEntity<List<Task>> getTasksToNotify(@PathVariable Long userId) {
        List<Task> task = taskService.getTasktoNotify(userId);
        if (task.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        try {
            if (task.getTaskid() == null || !task.getTaskid().equals(id)) {
                return ResponseEntity.badRequest().build();
            }
            // Actualizar la tarea utilizando el servicio
            Task updatedTask = taskService.updateTask(task);

            // Si no se pudo actualizar (por ejemplo, no existe), responde con 404
            if (updatedTask == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(updatedTask); // 200 OK con la tarea actualizada
        } catch (Exception e) {
            // Manejo de errores generales
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

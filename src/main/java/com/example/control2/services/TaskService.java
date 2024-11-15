package com.example.control2.services;

import com.example.control2.models.Notifier;
import com.example.control2.models.Task;
import com.example.control2.repositories.TaskRepositoryImpl;
import com.example.control2.repositories.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepositoryImpl taskRepository;

    @Autowired
    NotifierService notifierService;

    public Task getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    public Task saveTask(Task task) {return taskRepository.save(task);}
    public Task deleteTask(Task task) {return taskRepository.delete(task);}
    public Task updateTask(Task task) {return taskRepository.update(task);}

    public List<Task> getTasksByUser(Long userId) {return taskRepository.findByUser(userId);}
    public Task changeTaskState(Task task){return taskRepository.changeState(task);}

    public List<Task> filterTaskByState(Long userId, Boolean state) {
        return taskRepository.findByState(userId, state);
    }
    public List<Task> searchTaskByKeyword(Long userId, String keyword) {
        return taskRepository.findByKeyword(userId, keyword);
    }

    public List<Task> getTasktoNotify(Long userId) {
        Notifier notifier = notifierService.findByUserId(userId);
        int hoursInterval = notifierService.getNotificationHoursInterval(notifier);
        return taskRepository.findByFinishingDeadline(userId, hoursInterval);
    }
}

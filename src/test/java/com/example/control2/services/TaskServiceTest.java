package com.example.control2.services;

import com.example.control2.models.Notifier;
import com.example.control2.models.Task;
import com.example.control2.repositories.TaskRepository;
import com.example.control2.repositories.TaskRepositoryImpl;
import com.example.control2.services.NotifierService;
import com.example.control2.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepositoryImpl taskRepository;

    @Mock
    private NotifierService notifierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTasktoNotify() {
        // Mock de datos
        Long userId = 1L;
        Notifier notifier = new Notifier();
        notifier.setTimeunit("day");
        notifier.setAmount(1);

        Task task1 = new Task();
        task1.setTaskid(1L);
        task1.setTitle("Task 1");
        task1.setDeadline(LocalDate.now().plusDays(1));

        Task task2 = new Task();
        task2.setTaskid(2L);
        task2.setTitle("Task 2");
        task2.setDeadline(LocalDate.now().plusDays(2));

        List<Task> expectedTasks = Arrays.asList(task1, task2);

        // Configurar mocks
        when(notifierService.findByUserId(userId)).thenReturn(notifier);
        when(notifierService.getNotificationHoursInterval(notifier)).thenReturn(24); // 1 día = 24 horas
        when(taskRepository.findByFinishingDeadline(userId, 24)).thenReturn(expectedTasks);

        // Llamar al método que se está probando
        List<Task> resultTasks = taskService.getTasktoNotify(userId);

        // Verificaciones
        assertEquals(expectedTasks.size(), resultTasks.size());
        assertEquals(expectedTasks.get(0).getTitle(), resultTasks.get(0).getTitle());
        assertEquals(expectedTasks.get(1).getTitle(), resultTasks.get(1).getTitle());

        // Verificar interacciones con los mocks
        verify(notifierService, times(1)).findByUserId(userId);
        verify(notifierService, times(1)).getNotificationHoursInterval(notifier);
        verify(taskRepository, times(1)).findByFinishingDeadline(userId, 24);
    }
}

package com.example.control2.repositories;

import com.example.control2.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public Task findById(Long id) {
        return null;
    }

    @Override
    public List<Task> findByUser(Long userId) {
        return List.of();
    }

    @Override
    public Task save(Task task) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("INSERT INTO tasks (taskid,titulo,descripcion,status,fecha_vencimiento,studentId) VALUES (:taskid, :titulo, :descripcion, :status, :deadline, :studentid)")
                    .addParameter("taskid",task.getTaskId())
                    .addParameter("titulo", task.getTitle())
                    .addParameter("descripcion", task.getDescription())
                    .addParameter("status", task.getStatus())
                    .addParameter("deadline", task.getDeadline())
                    .addParameter("studentId", task.getStudentId())
                    .executeAndFetchFirst(Task.class);
        }
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

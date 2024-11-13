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
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM tasks WHERE taskid = :id")
                    .addParameter("taskid", id)
                    .executeAndFetchFirst(Task.class);
        }
    }

    @Override
    public List<Task> findByUser(Long userId) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM tasks WHERE studentid= :studentid")
                    .addParameter("studentid",userId)
                    .executeAndFetch(Task.class);
        }
    }

    @Override
    public Task save(Task task) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("INSERT INTO tasks (taskid,titulo,descripcion,status,deadline,studentId) VALUES (:taskid, :titulo, :descripcion, :status, :deadline, :studentid)")
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
    public Task update(Task task) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("UPDATE tasks SET titulo=: titulo, descripcion= :descripcion, deadline= :deadline, status= :status WHERE taskid= :taskid")
                    .addParameter("taskid", task.getTaskId())
                    .addParameter("titulo", task.getTitle())
                    .addParameter("descripcion", task.getDescription())
                    .addParameter("deadline", task.getDeadline())
                    .addParameter("status", task.getStatus())
                    .executeAndFetchFirst(Task.class);
        }
    }

    @Override
    public Task delete(Task task) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("DELETE from tasks WHERE taskid= :taskid")
                    .addParameter("taskid",task.getTaskId())
                    .executeAndFetchFirst(Task.class);
        }
    }

    @Override
    //En query, 1=true, 0=false, null=unknown
    //cambia a valor opuesto al que haya estado guardado
    public Task changeState(Task task) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("UPDATE tasks SET status = NOT status WHERE taskid= :taskid")
                    .addParameter("taskid", task.getTaskId())
                    .executeAndFetchFirst(Task.class);
        }
    }

    /**
    @Override
    public void findByState(Long userId, Boolean state) {

    }

    @Override
    public void findByKeyword(Long userId, String keyword) {

    }
    **/
}

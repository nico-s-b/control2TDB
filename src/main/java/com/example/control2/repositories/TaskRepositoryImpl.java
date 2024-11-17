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
    public List<Task> findByUser(Long userid) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM tasks WHERE userid= :userid")
                    .addParameter("userid", userid)
                    .executeAndFetch(Task.class);
        }
    }

    @Override
    public Task save(Task task) {
        try (org.sql2o.Connection con = sql2o.beginTransaction()) {
            con.createQuery("INSERT INTO tasks (title,description,status,deadline,userid) VALUES (:title, :description, :status, :deadline, :userid)")
                    .addParameter("title", task.getTitle())
                    .addParameter("description", task.getDescription())
                    .addParameter("status", task.getStatus())
                    .addParameter("deadline", task.getDeadline())
                    .addParameter("userid", task.getUserid())
                    .executeUpdate();

            Long generatedId = con.createQuery("SELECT currval('tasks_taskid_seq')")
                    .executeScalar(Long.class);

            task.setTaskid(generatedId);
            con.commit();
            return task;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la tarea", e);
        }
    }

    @Override
    public Task update(Task task) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("UPDATE tasks SET title=: title, description= :description, deadline= :deadline, status= :status WHERE taskid= :taskid")
                    .addParameter("taskid", task.getTaskid())
                    .addParameter("title", task.getTitle())
                    .addParameter("description", task.getDescription())
                    .addParameter("deadline", task.getDeadline())
                    .addParameter("status", task.getStatus())
                    .executeAndFetchFirst(Task.class);
        }
    }

    @Override
    public Task delete(Task task) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("DELETE from tasks WHERE taskid= :taskid")
                    .addParameter("taskid",task.getTaskid())
                    .executeAndFetchFirst(Task.class);
        }
    }

    @Override
    //En query, 1=true, 0=false, null=unknown
    //cambia a valor opuesto al que haya estado guardado
    public Task changeState(Task task) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("UPDATE tasks SET status = NOT status WHERE taskid= :taskid")
                    .addParameter("taskid", task.getTaskid())
                    .executeAndFetchFirst(Task.class);
        }
    }

    @Override
    public List<Task> findByState(Long userid, Boolean state) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM tasks WHERE userid= :userid AND status= :state")
                    .addParameter("userid",userid)
                    .addParameter("state",state)
                    .executeAndFetch(Task.class);
        }
    }

    @Override
    public List<Task> findByKeyword(Long userid, String keyword) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM tasks WHERE userid= :userid AND (title LIKE :keyword OR description LIKE :keyword)")
                    .addParameter("userid",userid)
                    .addParameter("keyword", "%" + keyword + "%")
                    .executeAndFetch(Task.class);
        }
    }

    @Override
    public List<Task> findByFinishingDeadline(Long userid, int hours) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery(
                    "SELECT * FROM tasks t " +
                            "WHERE t.userid = :userid AND " +
                            "CURRENT_DATE < t.deadline - interval ':hours hours'")
                    .addParameter("userid", userid)
                    .addParameter("hours", hours)
                    .executeAndFetch(Task.class);
        }
    }
}

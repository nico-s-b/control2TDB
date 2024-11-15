package com.example.control2.repositories;

import com.example.control2.models.Notifier;
import com.example.control2.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class NotifierRepositoryImpl implements NotifierRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public Notifier findByUser(Long userId) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM notifiers WHERE userid= :userid")
                    .addParameter("userid",userId)
                    .executeAndFetchFirst(Notifier.class);
        }
    }

    @Override
    public Notifier save(Notifier notifier) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("INSERT INTO tasks (userid,amount,timeunit,enabled) VALUES (:userid, :amount, :timeunit, :enabled)")
                    .addParameter("userid",notifier.getUserId())
                    .addParameter("amount", notifier.getAmount())
                    .addParameter("timeunit", notifier.getTimeunit())
                    .addParameter("enabled", notifier.isEnabled())
                    .executeAndFetchFirst(Notifier.class);
        }
    }

    @Override
    public Notifier delete(Notifier notifier) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("DELETE from notifiers WHERE userid= :userid")
                    .addParameter("userid",notifier.getUserId())
                    .executeAndFetchFirst(Notifier.class);
        }
    }
}

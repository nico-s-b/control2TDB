package com.example.control2.repositories;

import com.example.control2.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public User findUserByEmail(String email) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM users WHERE email = :email")
                    .addParameter("email", email)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public User findUserByUsername(String username) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM users WHERE username = :username")
                    .addParameter("username", username)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public User findUserById(Long id) {
        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM users WHERE userid = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public User saveUser(User user) {
        try (org.sql2o.Connection con = sql2o.open()) {
            con.createQuery("INSERT INTO users (userid, name, username, email) VALUES (:userid, :name, :username, :email)")
                    .addParameter("userid", user.getUserId())
                    .addParameter("name", user.getName())
                    .addParameter("username", user.getUsername())
                    .addParameter("email", user.getEmail())
                    .executeUpdate();
        }
        return user;
    }

    @Override
    public void deleteUser(User user) {
        try (org.sql2o.Connection con = sql2o.open()) {
            con.createQuery("DELETE FROM users WHERE userid = :id")
                    .addParameter("id", user.getUserId())
                    .executeUpdate();
        }
    }
}


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
        try (org.sql2o.Connection con = sql2o.beginTransaction()) {
            con.createQuery(
                            "INSERT INTO users (name, username, email, password, rol) " +
                                    "VALUES (:name, :username, :email, :password, :rol)")
                    .addParameter("name", user.getName())
                    .addParameter("username", user.getUsername())
                    .addParameter("email", user.getEmail())
                    .addParameter("password", user.getPassword())
                    .addParameter("rol", user.getRol())
                    .executeUpdate();

            Long generatedId = con.createQuery("SELECT currval('users_userid_seq')")
                    .executeScalar(Long.class);

            user.setUserid(generatedId);

            con.commit();

            return user;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el usuario", e);
        }
    }






    @Override
    public void deleteUser(User user) {
        try (org.sql2o.Connection con = sql2o.open()) {
            con.createQuery("DELETE FROM users WHERE userid = :id")
                    .addParameter("id", user.getUserid())
                    .executeUpdate();
        }
    }
}


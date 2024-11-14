package com.example.control2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //13/11, no c que me pasó pero me agilé y puse 'student' cuando en todos lados dice 'user' pero es muy tarde para mi
    private Long userId;
    private String name;
    private String password;
    private String username;
    private String email;
    private String rol;
}

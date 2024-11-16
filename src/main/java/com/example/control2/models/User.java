package com.example.control2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userid;
    private String name;
    private String password;
    private String username;
    private String email;
    private String rol;
}

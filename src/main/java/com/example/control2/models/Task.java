package com.example.control2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private Long taskId;
    private String title;
    private String description;
    //false = NO completo, true = completo
    private Boolean status;
    private ZonedDateTime deadline;
    private Long studentId;
}

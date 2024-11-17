package com.example.control2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private Long taskid;
    private String title;
    private String description;
    //false = NO completo, true = completo
    private Boolean status;
    private LocalDate deadline;
    private Long userid;
}

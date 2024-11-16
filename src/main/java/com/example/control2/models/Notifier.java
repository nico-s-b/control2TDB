package com.example.control2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notifier {
    private Long userid;
    private String timeunit;
    private int amount;
    private boolean enabled;
}

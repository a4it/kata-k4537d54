package com.codewars.katak4537d54.models.dtos;

import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubarrayResponse {

    private int maxSum;
    private String message;
}

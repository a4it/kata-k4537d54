package com.codewars.katak4537d54.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubarrayRequest {

    private int[] numbers;

}

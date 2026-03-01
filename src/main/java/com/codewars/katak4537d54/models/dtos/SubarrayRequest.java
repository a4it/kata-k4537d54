package com.codewars.katak4537d54.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubarrayRequest {

    @NotNull
    @Size(min = 0, message = "Array must contain at least one number")
    private int[] numbers;

}

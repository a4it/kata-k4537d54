package com.codewars.katak4537d54.models.dtos;

import org.springframework.boot.context.properties.bind.DefaultValue;

import lombok.Data;

@Data
public class SubarrayResponse {

    //default zero - implicit condition
    private int maxSum = 0;
    private int[] subarray;
    private String message;
}

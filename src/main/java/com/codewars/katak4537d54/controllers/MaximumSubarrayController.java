package com.codewars.katak4537d54.controllers;

import com.codewars.katak4537d54.models.dtos.SubarrayRequest;
import com.codewars.katak4537d54.models.dtos.SubarrayResponse;
import com.codewars.katak4537d54.services.CalculationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/maximum")
public class MaximumSubarrayController {

    private CalculationService calculationService;

    @PostMapping("/subarray")
    public ResponseEntity<SubarrayResponse> calculateMaxSumSubarray(SubarrayRequest request) {

        try {
            SubarrayResponse response = calculationService.max(request);

            return ResponseEntity.status(HttpStatus.OK.value()).body(subarrayResponse);
        } catch(Exception ex){
            Map<String, Map<String, Object>> result = new LinkedHashMap<>();



            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();
        }
    }
}

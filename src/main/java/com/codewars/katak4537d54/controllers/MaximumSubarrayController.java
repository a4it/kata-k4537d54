package com.codewars.katak4537d54.controllers;

import com.codewars.katak4537d54.models.dtos.SubarrayRequest;
import com.codewars.katak4537d54.models.dtos.SubarrayResponse;
import com.codewars.katak4537d54.services.CalculationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/maximum")
public class MaximumSubarrayController {

    private CalculationService calculationService;

    public MaximumSubarrayController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    /**
     * request through swagger ui {"numbers": [1, -1, 5]} result = 5
     *
     * @param request
     * @return
     */
    @PostMapping("/subarray")
    public ResponseEntity<SubarrayResponse> calculateMaxSumSubarray(@RequestBody SubarrayRequest request) {

        try {
            SubarrayResponse response = calculationService.max(request);

            return ResponseEntity.status(HttpStatus.OK.value()).body(response);

        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();
        }
    }
}

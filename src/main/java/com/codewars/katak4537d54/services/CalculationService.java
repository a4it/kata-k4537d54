package com.codewars.katak4537d54.services;

import com.codewars.katak4537d54.models.dtos.SubarrayRequest;
import com.codewars.katak4537d54.models.dtos.SubarrayResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CalculationService {


    /**
     * The maximum sum subarray problem consists in finding the maximum sum of a contiguous subsequence in an array or list of integers:
     * Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
     * Output: 6 (Sum of [4, -1, 2, 1])
     *
     * Easy case is when the list is made up of only positive numbers and the maximum sum is the sum of the whole array. If the list is made up of only negative numbers, return 0 instead.
     * Your solution should be fast, it will be tested on very large arrays so slow solutions will time out.
     *
     * Empty list is considered to have zero greatest sum. Note that the empty list or array is also a valid sublist/subarray.
     * @param request
     * @return SubarrayResponse
     */
    public SubarrayResponse max(SubarrayRequest request) {

        //Empty list is considered to have zero greatest sum
        if (request.getNumbers() == null || request.getNumbers().length == 0) {
            return new SubarrayResponse(0, "Array is Empty");
        }

        //If the list is made up of only negative numbers, return 0 instead
        boolean allNegative = Arrays.stream(request.getNumbers()).allMatch(n -> n < 0);
        if (allNegative) {
            return new SubarrayResponse(0, "All numbers are negative");
        }

        //Easy case is when the list is made up of only positive numbers and the maximum sum is the sum of the whole array
        return new SubarrayResponse(Arrays.stream(request.getNumbers()).reduce(0, Integer::sum), "Calculation complete");

    }

}

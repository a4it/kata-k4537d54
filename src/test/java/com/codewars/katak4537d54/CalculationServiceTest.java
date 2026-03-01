package com.codewars.katak4537d54;

import com.codewars.katak4537d54.models.dtos.SubarrayRequest;
import com.codewars.katak4537d54.models.dtos.SubarrayResponse;
import com.codewars.katak4537d54.services.CalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationServiceTest {

    private CalculationService calculationService;

    @BeforeEach
    void setUp() {
        calculationService = new CalculationService();
    }

    @Test
    void shouldReturnMaxSubarraySum_whenArrayContainsMixedNumbers() {
        int[] numbers = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        SubarrayRequest request = new SubarrayRequest(numbers);

        SubarrayResponse response = calculationService.max(request);

        assertEquals(6, response.getMaxSum());
    }


}

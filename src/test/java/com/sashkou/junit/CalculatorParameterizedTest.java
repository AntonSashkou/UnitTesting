package com.sashkou.junit;

import com.sashkou.Calculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CalculatorParameterizedTest {

    static Calculator calculator;

    @BeforeAll
    static void setUp() {
        calculator = new Calculator();
    }

    @ParameterizedTest
    @MethodSource(value =  "com.sashkou.junit.CalculatorTestData#multiplyTestData")
    @DisplayName("Parameterized simple multiplication should work")
    void testMultiplyParameterized(int[] data) {
        int expected = data[2];
        assertEquals(expected, calculator.multiply(data[0], data[1]),
                "Parameterized multiplication should work");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 12, 42})
    void testWithExplicitArgumentConversion(@ConvertWith(ToOctalStringArgumentConverter.class) String argument) {
        System.err.println(argument);
        assertNotNull(argument);
    }
}

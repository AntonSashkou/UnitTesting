package com.sashkou.junit;

import com.sashkou.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CalculatorTest {

    @Mock
    Calculator mockedCalculator;

    @Spy
    Calculator spyCalculator;

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @RepeatedTest(3)
    @DisplayName("Simple multiplication should work")
    void testMultiply() {
        assertEquals(20, calculator.multiply(4, 5),
                "Regular multiplication should work");
    }

    @Test
    @DisplayName("Mocked simple multiplication should work")
    void testMockedCalculator() {
        when(mockedCalculator.multiply(any(Integer.class), any(Integer.class))).thenReturn(20);
        assertEquals(20, mockedCalculator.multiply(4, 1)); // returns mocked result, as method implementation was mocked
        assertEquals(0, mockedCalculator.divide(5, 0)); // does nothing, mocked but mock method is not implemented

        verify(mockedCalculator, times(1)).multiply(any(Integer.class), any(Integer.class)); // verify mocked method was executed
        verify(mockedCalculator, times(1)).divide(5, 0); // verify mocked method was executed
        verify(mockedCalculator, times(0)).divide(0, 0); // verify mocked method was executed
    }


    /**
     * Difference between @Mock and @Spy
     * Both can be used to mock methods or fields.
     * The difference is that in mock, you are creating a complete mock or fake object while in spy,
     * there is the real object and you just spying or stubbing specific methods of it.
     * **/
    @Test
    public void testSpyCalculator() {
        Mockito.doReturn(100).when(spyCalculator).divide(any(Integer.class), any(Integer.class));
        assertEquals(spyCalculator.divide(100, 0), 100);
        assertEquals(spyCalculator.multiply(4, 5), 20);
    }
}

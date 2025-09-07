package br.com.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private CalculatorService service;

    @BeforeEach
    void setup() {
        service = new CalculatorService();
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "-1, 1, 0",
            "100, 200, 300"
    })
    @DisplayName("should return the correct sum")
    void sum(int a, int b, int expected) {
        assertEquals(expected, service.sum(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "5, 2, 3",
            "2, 5, -3"
    })
    void subtract(int a, int b, int expected) {
        assertEquals(expected, service.subtract(a, b));
    }

    @ParameterizedTest
    @CsvSource({
            "3, 4, 12",
            "-3, 4, -12",
            "0, 99, 0"
    })
    void multiply(int a, int b, int expected) {
        assertEquals(expected, service.multiply(a, b));
    }

    @Nested
    class Division {
        @ParameterizedTest
        @CsvSource({
                "8, 2, 4",
                "9, 3, 3"
        })
        void divisionOk(int a, int b, int expected) {
            assertEquals(expected, service.division(a, b));
        }

        @Test
        void divisionByZero() {
            var ex = assertThrows(IllegalArgumentException.class, () -> service.division(1, 0));
            assertEquals("division by zero", ex.getMessage());
        }
    }
}

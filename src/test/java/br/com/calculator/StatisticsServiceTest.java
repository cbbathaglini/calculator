package br.com.calculator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsServiceTest {

    private final StatisticsService service = new StatisticsService(new CalculatorService());

    @Test
    void media_ok() {
        assertEquals(3, service.mean(List.of(1, 2, 3, 6)));
    }


    @Test
    void mediana_impar() {
        assertEquals(3, service.median(List.of(1, 3, 3)));
    }

    @Test
    void mediana_par() {
        assertEquals(2, service.median(List.of(1, 5, 3, 2)));
    }

//    @Test
//    void listas_invalidas() {
//        IllegalArgumentException ex1 =
//                assertThrows(IllegalArgumentException.class, () -> service.mean(List.of()));
//        assertEquals("empty", ex1.getMessage());
//
//        IllegalArgumentException ex2 =
//                assertThrows(IllegalArgumentException.class, () -> service.median(List.of()));
//        assertEquals("empty", ex2.getMessage());
//    }

}
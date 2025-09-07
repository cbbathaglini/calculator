package br.com.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public int sum(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int division(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("division by zero");
        }
        return a / b;
    }
}

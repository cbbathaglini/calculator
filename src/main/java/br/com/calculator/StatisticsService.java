package br.com.calculator;

import br.com.calculator.CalculatorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StatisticsService {
    private final CalculatorService service;

    public StatisticsService(CalculatorService service) {
        this.service = service;
    }

    public int mean(List<Integer> valores) {
        if (valores.isEmpty()) throw new IllegalArgumentException("empty");
        int soma = 0;
        for (int v : valores) soma = service.sum(soma, v);
        return service.division(soma, valores.size());
    }

    public int median(List<Integer> valores) {
        if (valores.isEmpty()) throw new IllegalArgumentException("empty");
        List<Integer> c = new ArrayList<>(valores);
        Collections.sort(c);
        int n = c.size();
        if (n % 2 == 1) return c.get(n / 2);
        int a = c.get(n / 2 - 1);
        int b = c.get(n / 2);
        return service.division(service.sum(a, b), 2);
    }

}

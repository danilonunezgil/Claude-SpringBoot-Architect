package com.danno.claude_springboot_architect.service;

import org.springframework.stereotype.Service;

import com.danno.claude_springboot_architect.model.OperationType;

@Service
public class CalculatorService {
    
    public double calculate(int a, int b, OperationType operation) {
        return switch (operation) {
            case ADD -> a + b;
            case SUBTRACT -> a - b;
            case MULTIPLY -> a * b;
            case DIVIDE -> divide(a, b);
        };
    }

    private double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed");
        }
        return (double) a / b;
    }

}

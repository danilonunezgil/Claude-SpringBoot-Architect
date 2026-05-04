package com.danno.claude_springboot_architect.model;

public class OperationResult {
    
    private int a;
    private int b;
    private OperationType operation;
    private double result;

    public OperationResult(int a, int b, OperationType operation, double result) {
        this.a = a;
        this.b = b;
        this.operation = operation;
        this.result = result;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public OperationType getOperation() {
        return operation;
    }

    public double getResult() {
        return result;
    }

}

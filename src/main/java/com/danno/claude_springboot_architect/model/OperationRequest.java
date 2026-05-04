package com.danno.claude_springboot_architect.model;

public class OperationRequest {
    
    private int a;
    private int b;
    private OperationType operation;

    // Constructors
    public OperationRequest() {}

    public OperationRequest(int a, int b, OperationType operation) {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }

    // Getters and Setters
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }
}

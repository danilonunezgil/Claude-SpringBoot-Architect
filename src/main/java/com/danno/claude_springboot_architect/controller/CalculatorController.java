package com.danno.claude_springboot_architect.controller;

import com.danno.claude_springboot_architect.model.OperationRequest;
import com.danno.claude_springboot_architect.model.OperationResult;
import com.danno.claude_springboot_architect.service.CalculatorService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/calculate")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping
    public OperationResult calculate(@Valid @RequestBody OperationRequest request) {
        double result = calculatorService.calculate(
                request.getA(),
                request.getB(),
                request.getOperation()
        );

        return new OperationResult(
                request.getA(),
                request.getB(),
                request.getOperation(),
                result
        );
    }

}

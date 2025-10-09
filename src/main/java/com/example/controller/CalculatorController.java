package com.example.controller;

import com.example.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {
    
    @Autowired
    private CalculatorService calculatorService;
    
    @GetMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@RequestParam int a, @RequestParam int b) {
        int result = calculatorService.add(a, b);
        Map<String, Object> response = new HashMap<>();
        response.put("operation", "add");
        response.put("a", a);
        response.put("b", b);
        response.put("result", result);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/subtract")
    public ResponseEntity<Map<String, Object>> subtract(@RequestParam int a, @RequestParam int b) {
        int result = calculatorService.subtract(a, b);
        Map<String, Object> response = new HashMap<>();
        response.put("operation", "subtract");
        response.put("a", a);
        response.put("b", b);
        response.put("result", result);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/multiply")
    public ResponseEntity<Map<String, Object>> multiply(@RequestParam int a, @RequestParam int b) {
        int result = calculatorService.multiply(a, b);
        Map<String, Object> response = new HashMap<>();
        response.put("operation", "multiply");
        response.put("a", a);
        response.put("b", b);
        response.put("result", result);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/divide")
    public ResponseEntity<Map<String, Object>> divide(@RequestParam int a, @RequestParam int b) {
        try {
            double result = calculatorService.divide(a, b);
            Map<String, Object> response = new HashMap<>();
            response.put("operation", "divide");
            response.put("a", a);
            response.put("b", b);
            response.put("result", result);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}

package com.example.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    void testAdd() {
        int result = calculatorService.add(5, 3);
        assertEquals(8, result);
    }

    @Test
    void testAddNegativeNumbers() {
        int result = calculatorService.add(-5, -3);
        assertEquals(-8, result);
    }

    @Test
    void testSubtract() {
        int result = calculatorService.subtract(10, 4);
        assertEquals(6, result);
    }

    @Test
    void testMultiply() {
        int result = calculatorService.multiply(4, 5);
        assertEquals(20, result);
    }

    @Test
    void testMultiplyByZero() {
        int result = calculatorService.multiply(5, 0);
        assertEquals(0, result);
    }

    @Test
    void testDivide() {
        double result = calculatorService.divide(10, 2);
        assertEquals(5.0, result);
    }

    @Test
    void testDivideWithRemainder() {
        double result = calculatorService.divide(10, 3);
        assertEquals(3.333, result, 0.001);
    }

    @Test
    void testDivideByZeroThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.divide(10, 0);
        });
        assertEquals("Cannot divide by zero", exception.getMessage());
    }
}

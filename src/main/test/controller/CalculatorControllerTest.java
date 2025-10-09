package com.example.controller;

import com.example.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService calculatorService;

    @Test
    void testAdd() throws Exception {
        when(calculatorService.add(5, 3)).thenReturn(8);

        mockMvc.perform(get("/api/calculator/add")
                        .param("a", "5")
                        .param("b", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(8))
                .andExpect(jsonPath("$.operation").value("add"));
    }

    @Test
    void testSubtract() throws Exception {
        when(calculatorService.subtract(10, 4)).thenReturn(6);

        mockMvc.perform(get("/api/calculator/subtract")
                        .param("a", "10")
                        .param("b", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(6));
    }

    @Test
    void testMultiply() throws Exception {
        when(calculatorService.multiply(4, 5)).thenReturn(20);

        mockMvc.perform(get("/api/calculator/multiply")
                        .param("a", "4")
                        .param("b", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(20));
    }

    @Test
    void testDivide() throws Exception {
        when(calculatorService.divide(10, 2)).thenReturn(5.0);

        mockMvc.perform(get("/api/calculator/divide")
                        .param("a", "10")
                        .param("b", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(5.0));
    }

    @Test
    void testDivideByZero() throws Exception {
        when(calculatorService.divide(10, 0)).thenThrow(new IllegalArgumentException("Cannot divide by zero"));

        mockMvc.perform(get("/api/calculator/divide")
                        .param("a", "10")
                        .param("b", "0"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Cannot divide by zero"));
    }
}

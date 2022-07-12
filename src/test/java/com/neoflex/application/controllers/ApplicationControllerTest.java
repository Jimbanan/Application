package com.neoflex.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoflex.application.dto.LoanApplicationRequestDTO;
import com.neoflex.application.dto.LoanOfferDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationControllerTest {

    @MockBean
    private ApplicationController applicationController;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void application() throws Exception {
        mockMvc.perform(post("/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(LoanApplicationRequestDTO.builder()
                                .amount(BigDecimal.valueOf(10000))
                                .term(20)
                                .firstName("Николай")
                                .lastName("Козьяков")
                                .middleName("Николаевич")
                                .email("uservice371@mail.ru")
                                .birthdate(LocalDate.of(1980, 9, 29))
                                .passportSeries("1234")
                                .passportNumber("123456")
                                .applicationId(1L)
                                .build())))
                .andExpect(status().isOk());

    }

    @Test
    void offer() throws Exception {

        mockMvc.perform(put("/application/offer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(LoanOfferDTO.builder()
                                .applicationId(1L)
                                .requestedAmount(BigDecimal.valueOf(10000))
                                .totalAmount(BigDecimal.valueOf(10000))
                                .term(6)
                                .monthlyPayment(BigDecimal.valueOf(10000))
                                .rate(BigDecimal.valueOf(20))
                                .isInsuranceEnabled(true)
                                .isSalaryClient(true)
                                .build())))
                .andExpect(status().isOk());

    }
}
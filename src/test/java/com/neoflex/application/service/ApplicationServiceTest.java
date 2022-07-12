package com.neoflex.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoflex.application.controllers.ApplicationController;
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

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationServiceTest {

    @MockBean
    private ApplicationService applicationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getOffersList() throws Exception {

        LoanApplicationRequestDTO loanApplicationRequestDTO = LoanApplicationRequestDTO.builder()
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
                .build();

        mockMvc.perform(post("/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanApplicationRequestDTO)))
                .andExpect(status().isOk());

        Assertions.assertNotNull(applicationService.getOffersList(loanApplicationRequestDTO));

    }

    @Test
    void confirmOffer() throws Exception {

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
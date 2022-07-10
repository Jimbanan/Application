package com.neoflex.application.controllers;

import com.neoflex.application.dto.LoanApplicationRequestDTO;
import com.neoflex.application.dto.LoanOfferDTO;
import com.neoflex.application.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(("/application"))
@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "ApplicationController", description = "Кредитный конвейер")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    @Operation(description = "Формирование списка кредитных предложение")
    public List<LoanOfferDTO> application(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("application(): List<LoanOfferDTO> Получение списка LoanOfferDTO из MC Deal");
        return applicationService.getOffersList(loanApplicationRequestDTO);
    }

    @PutMapping("/offer")
    @Operation(description = "Добавление полученного офера в БД")
    public void offer(@RequestBody LoanOfferDTO loanOfferDTO) {
        applicationService.confirmOffer(loanOfferDTO);
        log.info("offer(): void Передача выбранного офера в MC Deal");
    }

}

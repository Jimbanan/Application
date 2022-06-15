package com.neoflex.application.controllers;

import com.neoflex.application.dto.LoanApplicationRequestDTO;
import com.neoflex.application.dto.LoanOfferDTO;
import com.neoflex.application.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(("/application"))
@RestController
@RequiredArgsConstructor
@Tag(name = "ApplicationController", description = "Кредитный конвейер")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    @Operation(description = "Формирование списка кредитных предложение")
    List<LoanOfferDTO> application(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO) {
        List<LoanOfferDTO> offersList = applicationService.getOffersList(loanApplicationRequestDTO);
        return offersList;
    }

    @PutMapping("/offer")
    @Operation(description = "Добавление полученного офера в БД")
    void offer(@RequestBody LoanOfferDTO loanOfferDTO) {
        applicationService.confirmOffer(loanOfferDTO);
    }

}

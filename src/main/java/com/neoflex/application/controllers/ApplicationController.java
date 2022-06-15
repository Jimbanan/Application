package com.neoflex.application.controllers;

import com.neoflex.application.dto.LoanApplicationRequestDTO;
import com.neoflex.application.dto.LoanOfferDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(("/application"))
@RestController
@Tag(name = "ApplicationController", description = "Кредитный конвейер")
public class ApplicationController {


    @PostMapping("/application")
    List<LoanOfferDTO> application(@RequestParam LoanApplicationRequestDTO loanApplicationRequestDTO) {

        return null;
    }

    @PutMapping("/application/offer")
    void offer(@RequestParam LoanOfferDTO loanOfferDTO) {

    }

}

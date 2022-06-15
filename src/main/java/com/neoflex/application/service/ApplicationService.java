package com.neoflex.application.service;

import com.neoflex.application.dto.LoanApplicationRequestDTO;
import com.neoflex.application.dto.LoanOfferDTO;
import com.neoflex.application.feignclient.DealMC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationService {

    private final DealMC dealMC;

    public List<LoanOfferDTO> getOffersList(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("getOffersList(): List<LoanOfferDTO> Получение списка оферов");
        return dealMC.getOffersListDeal(loanApplicationRequestDTO);
    }

    public void confirmOffer(LoanOfferDTO loanOfferDTO) {
        dealMC.offerConfirmDeal(loanOfferDTO);
        log.info("getOffersList(): void Передача выбранного офера");
    }

}

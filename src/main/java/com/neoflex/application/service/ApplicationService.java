package com.neoflex.application.service;

import com.neoflex.application.dto.LoanApplicationRequestDTO;
import com.neoflex.application.dto.LoanOfferDTO;
import com.neoflex.application.feignclient.DealMC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationService {

    private final DealMC dealMC;
    private final Scoring scoring;

    public List<LoanOfferDTO> getOffersList(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        scoring.checkScoringData(loanApplicationRequestDTO);

        log.info("getOffersList(): List<LoanOfferDTO> Получение списка оферов");
        return dealMC.getOffersListDeal(loanApplicationRequestDTO);
    }

    public void confirmOffer(LoanOfferDTO loanOfferDTO) {
        log.info("getOffersList(): void Передача выбранного офера");
        dealMC.offerConfirmDeal(loanOfferDTO);
    }

}

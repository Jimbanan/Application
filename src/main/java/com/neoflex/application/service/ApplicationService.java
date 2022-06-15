package com.neoflex.application.service;

import com.neoflex.application.dto.LoanApplicationRequestDTO;
import com.neoflex.application.dto.LoanOfferDTO;
import com.neoflex.application.feignclient.DealMC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final DealMC dealMC;

    public List<LoanOfferDTO> getOffersList(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        return dealMC.getOffersListDeal(loanApplicationRequestDTO);
    }

    public void confirmOffer(LoanOfferDTO loanOfferDTO) {
        dealMC.offerConfirmDeal(loanOfferDTO);
    }

}

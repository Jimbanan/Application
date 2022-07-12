package com.neoflex.application.feignclient;

import com.neoflex.application.dto.LoanApplicationRequestDTO;
import com.neoflex.application.dto.LoanOfferDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "dealMC", url = "${deal.hostname}")
public interface DealMC {

    @PostMapping("/deal/application")
    List<LoanOfferDTO> getOffersListDeal(@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO);

    @PutMapping("/deal/offer")
    ResponseEntity<?> offerConfirmDeal(@RequestBody LoanOfferDTO loanOfferDTO);

}

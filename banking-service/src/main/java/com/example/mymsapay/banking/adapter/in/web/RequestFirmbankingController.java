package com.example.mymsapay.banking.adapter.in.web;

import com.example.mymsapay.WebAdapter;
import com.example.mymsapay.banking.adapter.in.web.dto.RequestFirmbankingRequest;
import com.example.mymsapay.banking.application.port.in.RequestFirmbankingCommand;
import com.example.mymsapay.banking.application.port.in.RequestFirmbankingUseCase;
import com.example.mymsapay.banking.domain.RequestFirmbanking;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/banking")
public class RequestFirmbankingController {

    private final RequestFirmbankingUseCase requestFirmbankingUseCase;

    @PostMapping("/firmbanking/request")
    public RequestFirmbanking requestFirmbanking(@RequestBody RequestFirmbankingRequest request) {
        RequestFirmbankingCommand command = RequestFirmbankingCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .toBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAmount(request.getMoneyAmount())
                .build();

        return requestFirmbankingUseCase.requestFirmbanking(command);
    }
}

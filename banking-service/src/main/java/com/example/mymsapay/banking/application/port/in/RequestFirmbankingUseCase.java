package com.example.mymsapay.banking.application.port.in;

import com.example.mymsapay.banking.domain.RequestFirmbanking;

public interface RequestFirmbankingUseCase {
    RequestFirmbanking requestFirmbanking(RequestFirmbankingCommand command);
}

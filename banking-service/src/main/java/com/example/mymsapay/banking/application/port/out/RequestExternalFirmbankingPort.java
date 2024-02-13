package com.example.mymsapay.banking.application.port.out;

import com.example.mymsapay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.example.mymsapay.banking.adapter.out.external.bank.FirmBankingResult;

public interface RequestExternalFirmbankingPort {
    FirmBankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request);
}

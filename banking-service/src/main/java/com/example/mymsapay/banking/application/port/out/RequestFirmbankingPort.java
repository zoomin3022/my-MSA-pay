package com.example.mymsapay.banking.application.port.out;

import com.example.mymsapay.banking.adapter.out.persistence.firmbanking.RequestFirmbankingEntity;
import com.example.mymsapay.banking.domain.RequestFirmbanking;

public interface RequestFirmbankingPort {

    RequestFirmbankingEntity createRequestFirmbanking(
            RequestFirmbanking.FromBankName fromBankName,
            RequestFirmbanking.FromBankAccountNumber fromBankAccountNumber,
            RequestFirmbanking.ToBankName toBankName,
            RequestFirmbanking.ToBankAccountNumber toBankAccountNumber,
            RequestFirmbanking.MoneyAmount moneyAmount,
            RequestFirmbanking.FirmbankingRequestStatus firmbankingRequestStatus
    );

    RequestFirmbankingEntity updateRequestFirmbanking(RequestFirmbankingEntity requestFirmbankingEntity);
}

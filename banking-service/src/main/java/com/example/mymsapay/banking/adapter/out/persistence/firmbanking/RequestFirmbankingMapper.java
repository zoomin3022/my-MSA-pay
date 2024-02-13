package com.example.mymsapay.banking.adapter.out.persistence.firmbanking;

import com.example.mymsapay.banking.domain.RequestFirmbanking;
import org.springframework.stereotype.Component;

@Component
public class RequestFirmbankingMapper {
    public RequestFirmbanking entityToDomain(RequestFirmbankingEntity entity) {
        return RequestFirmbanking.generateFirmbankingRequest(
                new RequestFirmbanking.RequestFirmbankingId(entity.getRequestFirmbankingId()),
                new RequestFirmbanking.FromBankName(entity.getFromBankName()),
                new RequestFirmbanking.FromBankAccountNumber(entity.getFromBankAccountNumber()),
                new RequestFirmbanking.ToBankName(entity.getToBankName()),
                new RequestFirmbanking.ToBankAccountNumber(entity.getToBankAccountNumber()),
                new RequestFirmbanking.MoneyAmount(entity.getMoneyAmount()),
                new RequestFirmbanking.FirmbankingRequestStatus(entity.getFirmbankingRequestStatus())
        );
    }
}

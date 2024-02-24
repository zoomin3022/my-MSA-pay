package com.example.mymsapay.banking.adapter.out.persistence.firmbanking;

import com.example.mymsapay.common.PersistenceAdapter;
import com.example.mymsapay.banking.application.port.out.RequestFirmbankingPort;
import com.example.mymsapay.banking.domain.RequestFirmbanking;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RequestFirmbankingPersistenceAdapter implements RequestFirmbankingPort {

    private final SpringDataRequestFirmbanking requestFirmbankingRepository;
    @Override
    public RequestFirmbankingEntity createRequestFirmbanking(RequestFirmbanking.FromBankName fromBankName, RequestFirmbanking.FromBankAccountNumber fromBankAccountNumber, RequestFirmbanking.ToBankName toBankName, RequestFirmbanking.ToBankAccountNumber toBankAccountNumber, RequestFirmbanking.MoneyAmount moneyAmount, RequestFirmbanking.FirmbankingRequestStatus firmbankingRequestStatus) {
        return requestFirmbankingRepository.save(
                RequestFirmbankingEntity.builder()
                        .fromBankName(fromBankName.getNameValue())
                        .fromBankAccountNumber(fromBankAccountNumber.getBankAccountNumberValue())
                        .toBankName(toBankName.getNameValue())
                        .toBankAccountNumber(toBankAccountNumber.getBankAccountNumberValue())
                        .moneyAmount(moneyAmount.getMoneyAmountValue())
                        .firmbankingRequestStatus(firmbankingRequestStatus)
                        .build());
    }

    @Override
    public RequestFirmbankingEntity updateRequestFirmbanking(RequestFirmbankingEntity requestFirmbankingEntity) {
        return requestFirmbankingRepository.save(requestFirmbankingEntity);
    }
}

package com.example.mymsapay.banking.application.service;

import com.example.mymsapay.common.UseCase;
import com.example.mymsapay.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.example.mymsapay.banking.adapter.out.external.bank.FirmBankingResult;
import com.example.mymsapay.banking.adapter.out.persistence.firmbanking.RequestFirmbankingEntity;
import com.example.mymsapay.banking.adapter.out.persistence.firmbanking.RequestFirmbankingMapper;
import com.example.mymsapay.banking.application.port.in.RequestFirmbankingCommand;
import com.example.mymsapay.banking.application.port.in.RequestFirmbankingUseCase;
import com.example.mymsapay.banking.application.port.out.RequestExternalFirmbankingPort;
import com.example.mymsapay.banking.application.port.out.RequestFirmbankingPort;
import com.example.mymsapay.banking.domain.RequestFirmbanking;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RequestFirmbankingService implements RequestFirmbankingUseCase {
    private final RequestFirmbankingMapper mapper;
    private final RequestFirmbankingPort requestFirmbankingPort;
    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;

    @Override
    public RequestFirmbanking requestFirmbanking(RequestFirmbankingCommand command) {

        //Biz logic
        // a->b 계좌

        // 1. 요청 상태로 write
        RequestFirmbankingEntity entity = requestFirmbankingPort.createRequestFirmbanking(
                new RequestFirmbanking.FromBankName(command.getFromBankName()),
                new RequestFirmbanking.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new RequestFirmbanking.ToBankName(command.getToBankName()),
                new RequestFirmbanking.ToBankAccountNumber(command.getToBankAccountNumber()),
                new RequestFirmbanking.MoneyAmount(command.getMoneyAmount()),
                RequestFirmbanking.FirmbankingRequestStatus.REQUESTING);
        // 2. 회원끼리의 거래면 머니만 주고받고 외부 계좌면 외부 은행에 펌뱅킹 요청 일단은 여기서는 간단하게 하려고 무조건 요청이라 침
        FirmBankingResult firmBankingResult = requestExternalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber()));
        // 3. 펌뱅킹 요청이 완료되면 요청 상태를 완료로 변경
        if (firmBankingResult.getResult() == FirmBankingResult.Result.SUCCESS) {
            entity.setFirmbankingRequestStatus(RequestFirmbanking.FirmbankingRequestStatus.SUCCESS);
        } else {
            entity.setFirmbankingRequestStatus(RequestFirmbanking.FirmbankingRequestStatus.FAIL);
        }

        return mapper.entityToDomain(requestFirmbankingPort.updateRequestFirmbanking(entity));
    }
}

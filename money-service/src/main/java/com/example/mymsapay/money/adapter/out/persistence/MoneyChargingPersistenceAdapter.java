package com.example.mymsapay.money.adapter.out.persistence;

import com.example.mymsapay.PersistenceAdapter;
import com.example.mymsapay.banking.application.port.out.FindBankAccountPort;
import com.example.mymsapay.banking.application.port.out.RegisterBankAccountPort;
import com.example.mymsapay.banking.domain.RegisteredBankAccount;
import com.example.mymsapay.banking.exception.BankAccountException;
import com.example.mymsapay.banking.exception.BankAccountExceptionType;
import com.example.mymsapay.money.application.port.out.ChargingMoneyPort;
import com.example.mymsapay.money.domain.ChargingMoneyStatus;
import com.example.mymsapay.money.domain.MoneyCharging;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChargingPersistenceAdapter implements ChargingMoneyPort {
    private final SpringDataMoneyChargingRepository chargingRepository;

    @Override
    public MoneyChargingEntity chargeMoney(MoneyCharging.TargetMembershipId targetMembershipId, MoneyCharging.ChargingMoneyAmount chargingMoneyAmount, ChargingMoneyStatus chargingMoneyStatus) {
        return chargingRepository.save(
                new MoneyChargingEntity(
                        targetMembershipId.getTargetMembershipId(), chargingMoneyStatus, chargingMoneyAmount.getChargingMoneyAmount()));
    }
}

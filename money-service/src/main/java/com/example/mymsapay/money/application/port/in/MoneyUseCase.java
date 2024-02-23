package com.example.mymsapay.money.application.port.in;

import com.example.mymsapay.money.domain.MemberMoney;
import com.example.mymsapay.money.domain.MoneyCharging;

public interface MoneyUseCase {
    MoneyCharging chargeMoney(MoneyChargingCommand command);

    MemberMoney createMoneyAccount(Long membershipId);

}
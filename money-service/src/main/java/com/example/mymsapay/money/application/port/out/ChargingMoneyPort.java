package com.example.mymsapay.money.application.port.out;

import com.example.mymsapay.money.adapter.out.persistence.MemberMoneyEntity;
import com.example.mymsapay.money.adapter.out.persistence.MoneyChargingEntity;
import com.example.mymsapay.money.domain.ChargingMoneyStatus;
import com.example.mymsapay.money.domain.MemberMoney;
import com.example.mymsapay.money.domain.MoneyCharging;

public interface ChargingMoneyPort {

    MemberMoneyEntity createMemberMoney(MemberMoney.MembershipId membershipId);

    MoneyChargingEntity chargeMoney(
            MoneyCharging.TargetMembershipId targetMembershipId,
            MoneyCharging.ChargingMoneyAmount chargingMoneyAmount,
            ChargingMoneyStatus chargingMoneyStatus
    );

    MemberMoneyEntity chargeBalance(
            MemberMoney.MemberMoneyId memberMoneyId,
            int chargeAmount
    );
}

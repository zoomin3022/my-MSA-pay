package com.example.mymsapay.money.adapter.out.persistence;

import com.example.mymsapay.common.PersistenceAdapter;
import com.example.mymsapay.money.application.port.out.ChargingMoneyPort;
import com.example.mymsapay.money.domain.ChargingMoneyStatus;
import com.example.mymsapay.money.domain.MemberMoney;
import com.example.mymsapay.money.domain.MoneyCharging;
import com.example.mymsapay.money.exception.MoneyException;
import com.example.mymsapay.money.exception.MoneyExceptionType;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChargingPersistenceAdapter implements ChargingMoneyPort {
    private final SpringDataMoneyChargingRepository chargingRepository;
    private final SpringDataMemberMoneyRepository memberMoneyRepository;

    @Override
    public MoneyChargingEntity chargeMoney(MoneyCharging.TargetMembershipId targetMembershipId, MoneyCharging.ChargingMoneyAmount chargingMoneyAmount, ChargingMoneyStatus chargingMoneyStatus) {
        return chargingRepository.save(
                new MoneyChargingEntity(
                        targetMembershipId.getTargetMembershipId(), chargingMoneyStatus, chargingMoneyAmount.getChargingMoneyAmount()));
    }

    @Override
    public MoneyChargingEntity updateMoneyCharging(MoneyChargingEntity moneyChargingEntity) {
        return chargingRepository.save(moneyChargingEntity);
    }


    @Override
    public MemberMoneyEntity createMemberMoney(Long membershipId) {
        return memberMoneyRepository.save(MemberMoneyEntity
                .builder()
                .membershipId(membershipId)
                .balance(0)
                .build());
    }

    @Override
    public MemberMoneyEntity findMemberMoneyEntityByMembershipId(Long membershipId) {
        return memberMoneyRepository.findByMembershipId(membershipId)
                .orElseThrow(() -> new MoneyException(MoneyExceptionType.MONEY_ACCOUNT_NOT_EXISTS));
    }

    @Override
    public MemberMoneyEntity chargeBalance(MemberMoney.MemberMoneyId memberMoneyId, int chargeAmount) {
        MemberMoneyEntity memberMoneyEntity = memberMoneyRepository.findById(memberMoneyId.getMemberMoneyId())
                .orElseThrow(() -> new MoneyException(MoneyExceptionType.MONEY_ACCOUNT_NOT_EXISTS));

        memberMoneyEntity.chargeBalance(chargeAmount);

        return memberMoneyRepository.save(memberMoneyEntity);
    }
}

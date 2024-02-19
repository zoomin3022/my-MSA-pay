package com.example.mymsapay.money.adapter.out.persistence;

import com.example.mymsapay.money.domain.MemberMoney;
import com.example.mymsapay.money.domain.MoneyCharging;
import org.springframework.stereotype.Component;

@Component
public class MemberMoneyMapper {
    public MemberMoney entityToDomain(
            MemberMoneyEntity entity) {
        return MemberMoney.generateMemberMoney(
                new MemberMoney.MemberMoneyId(entity.getMemberMoneyId()),
                new MemberMoney.MembershipId(entity.getMembershipId()),
                new MemberMoney.Balance(entity.getBalance()));
    }
}


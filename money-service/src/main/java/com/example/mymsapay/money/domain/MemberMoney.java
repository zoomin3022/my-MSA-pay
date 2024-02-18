package com.example.mymsapay.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberMoney {

    private final Long memberMoneyId;

    private final Long membershipId;

    private final int balance;

    public static MemberMoney generateMemberMoney(MemberMoneyId memberMoneyId, MembershipId membershipId, Balance balance) {
        return new MemberMoney(memberMoneyId.getMemberMoneyId(), membershipId.getMembershipId(), balance.getBalance());
    }

    @Value
    public static class MemberMoneyId {
        public MemberMoneyId(Long value) {
            this.memberMoneyId = value;
        }
        Long memberMoneyId;
    }

    @Value
    public static class MembershipId {
        public MembershipId(Long value) {
            this.membershipId = value;
        }
        Long membershipId;
    }

    @Value
    public static class Balance {
        public Balance(int value) {
            this.balance = value;
        }
        int balance;
    }
}

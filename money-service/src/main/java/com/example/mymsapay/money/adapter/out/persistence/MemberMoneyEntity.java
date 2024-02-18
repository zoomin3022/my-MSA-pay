package com.example.mymsapay.money.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_money")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberMoneyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMoneyId;

    private Long membershipId;

    private int balance;

    public void chargeBalance(int chargeAmount) {
        this.balance += chargeAmount;
    }

    @Builder
    public MemberMoneyEntity(Long membershipId, int balance) {
        this.membershipId = membershipId;
        this.balance = balance;
    }
}

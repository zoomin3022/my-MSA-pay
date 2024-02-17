package com.example.mymsapay.money.adapter.in.web.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MoneyChargingRequest {
    @Positive
    private Long targetMembershipId;

    @Positive
    private int moneyAmount;
}


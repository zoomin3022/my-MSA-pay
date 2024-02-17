package com.example.mymsapay.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MoneyCharging {
    private final Long moneyChargingId;
    private final Long targetMembershipId;

    private final int chargingMoneyAmount;

    private final ChargingMoneyStatus chargingMoneyStatus;

    private LocalDateTime createdAt;


    public static MoneyCharging generateMoneyCharging(MoneyChargingId moneyChargingId, TargetMembershipId targetMembershipId, ChargingMoneyAmount chargingMoneyAmount, ChargingMoneyStatus chargingMoneyStatus, CreatedAt createdAt) {
        return new MoneyCharging(moneyChargingId.getMoneyChargingId(), targetMembershipId.getTargetMembershipId(), chargingMoneyAmount.chargingMoneyAmount, chargingMoneyStatus, createdAt.getCreatedAt());
    }

    @Value
    public static class MoneyChargingId {
        public MoneyChargingId(Long value) {
            this.moneyChargingId = value;
        }

        Long moneyChargingId;
    }

    @Value
    public static class TargetMembershipId {
        public TargetMembershipId(Long value) {
            this.targetMembershipId = value;
        }

        Long targetMembershipId;
    }

    @Value
    public static class ChargingMoneyAmount {
        public ChargingMoneyAmount(int value) {
            this.chargingMoneyAmount = value;
        }

        int chargingMoneyAmount;
    }

    @Value
    public static class CreatedAt {
        public CreatedAt(LocalDateTime value) {
            this.createdAt = value;
        }

        LocalDateTime createdAt;
    }
}

package com.example.mymsapay.money.adapter.out.persistence;

import com.example.mymsapay.money.domain.ChargingMoneyStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "money_charging")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MoneyChargingEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moneyChargingId;

    private Long targetMembershipId;

    @Setter
    @Enumerated(EnumType.STRING)
    private ChargingMoneyStatus chargingMoneyStatus;

    private int chargingMoneyAmount;

    @Builder
    public MoneyChargingEntity(Long targetMembershipId, ChargingMoneyStatus chargingMoneyStatus, int chargingMoneyAmount) {
        this.targetMembershipId = targetMembershipId;
        this.chargingMoneyStatus = chargingMoneyStatus;
        this.chargingMoneyAmount = chargingMoneyAmount;
    }
}

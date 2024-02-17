package com.example.mymsapay.money.adapter.out.persistence;

import com.example.mymsapay.money.domain.MoneyCharging;
import org.springframework.stereotype.Component;

@Component
public class MoneyChargingMapper {
    public MoneyCharging entityToDomain(
            MoneyChargingEntity entity) {
        return MoneyCharging.generateMoneyCharging(
                new MoneyCharging.MoneyChargingId(entity.getMoneyChargingId()),
                new MoneyCharging.TargetMembershipId(entity.getTargetMembershipId()),
                new MoneyCharging.ChargingMoneyAmount(entity.getChargingMoneyAmount()),
                entity.getChargingMoneyStatus(),
                new MoneyCharging.CreatedAt(entity.getCreatedAt()));
    }
}

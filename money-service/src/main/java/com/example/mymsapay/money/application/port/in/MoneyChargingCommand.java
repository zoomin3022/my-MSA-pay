package com.example.mymsapay.money.application.port.in;

import com.example.mymsapay.common.SelfValidating;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class MoneyChargingCommand extends SelfValidating<MoneyChargingCommand> {
    @Positive
    private Long targetMembershipId;

    @Positive
    private int chargingMoneyAmount;

    public MoneyChargingCommand(Long targetMembershipId, int chargingMoneyAmount) {
        this.targetMembershipId = targetMembershipId;
        this.chargingMoneyAmount = chargingMoneyAmount;
        this.validateSelf();
    }
}

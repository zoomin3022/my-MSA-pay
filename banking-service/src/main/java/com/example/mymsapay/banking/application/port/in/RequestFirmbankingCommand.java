package com.example.mymsapay.banking.application.port.in;

import com.example.mymsapay.SelfValidating;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class RequestFirmbankingCommand extends SelfValidating<RequestFirmbankingCommand> {
    @NotNull
    private final String fromBankName;
    @NotNull
    private final String fromBankAccountNumber;
    @NotNull
    private final String toBankName;
    @NotNull
    private final String toBankAccountNumber;
    @Positive
    private final int moneyAmount;

}

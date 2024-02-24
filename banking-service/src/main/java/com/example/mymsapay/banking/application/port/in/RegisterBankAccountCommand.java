package com.example.mymsapay.banking.application.port.in;


import com.example.mymsapay.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterBankAccountCommand extends SelfValidating<RegisterBankAccountCommand> {

    @Positive
    private final Long membershipId;

    @NotNull
    private final String bankName;

    @NotNull
    private final String bankAccountNumber;

    private final boolean isValid;

    public RegisterBankAccountCommand(Long membershipId, String bankName, String bankAccountNumber, boolean isValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.isValid = isValid;
        this.validateSelf();
    }
}

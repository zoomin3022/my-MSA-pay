package com.example.mymsapay.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {
    @Getter
    private final Long bankAccountId;
    @Getter
    private final Long membershipId;
    @Getter
    private final String bankName;
    @Getter
    private final String bankAccountNumber;
    @Getter
    private final boolean linkedStatusIsValid;

    public static RegisteredBankAccount generateBankAccount(
            BankAccountId bankAccountId, MembershipId membershipId, BankName bankName,
            BankAccountNumber bankAccountNumber, BankAccountLinkedIsValid bankAccountLinkedIsValid) {
        return new RegisteredBankAccount(
                bankAccountId.bankAccountId,
                membershipId.membershipId,
                bankName.nameValue,
                bankAccountNumber.bankAccountNumberValue,
                bankAccountLinkedIsValid.isValidValue
        );
    }

    @Value
    public static class BankAccountId {
        public BankAccountId(Long value) {
            this.bankAccountId = value;
        }

        Long bankAccountId;
    }

    @Value
    public static class MembershipId {
        public MembershipId(Long value) {
            this.membershipId = value;
        }

        Long membershipId;
    }

    @Value
    public static class BankName {
        public BankName(String value) {
            this.nameValue = value;
        }

        String nameValue;
    }

    @Value
    public static class BankAccountNumber {
        public BankAccountNumber(String value) {
            this.bankAccountNumberValue = value;
        }

        String bankAccountNumberValue;
    }

    @Value
    public static class MembershipAddress {
        public MembershipAddress(String value) {
            this.addressValue = value;
        }

        String addressValue;
    }

    @Value
    public static class BankAccountLinkedIsValid {
        public BankAccountLinkedIsValid(boolean value) {
            this.isValidValue = value;
        }

        boolean isValidValue;
    }
}

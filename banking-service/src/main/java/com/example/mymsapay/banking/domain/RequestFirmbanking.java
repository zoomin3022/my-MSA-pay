package com.example.mymsapay.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RequestFirmbanking {
    private final Long requestFirmbankingId;
    private final String fromBankName;
    private final String fromBankAccountNumber;
    private final String toBankName;
    private final String toBankAccountNumber;
    private final int moneyAmount;
    private final FirmbankingRequestStatus firmbankingRequestStatus;

    public static enum FirmbankingRequestStatus {
        SUCCESS, FAIL, REQUESTING
    }

    public static RequestFirmbanking generateFirmbankingRequest(
            RequestFirmbankingId requestFirmbankingId, FromBankName fromBankName,
            FromBankAccountNumber fromBankAccountNumber, ToBankName toBankName,
            ToBankAccountNumber toBankAccountNumber, MoneyAmount moneyAmount,
            FirmbankingRequestStatus firmbankingRequestStatus) {
        return new RequestFirmbanking(
                requestFirmbankingId.firmbankingRequestId,
                fromBankName.nameValue,
                fromBankAccountNumber.bankAccountNumberValue,
                toBankName.nameValue,
                toBankAccountNumber.bankAccountNumberValue,
                moneyAmount.moneyAmountValue,
                firmbankingRequestStatus
        );
    }

    @Value
    public static class RequestFirmbankingId {
        public RequestFirmbankingId(Long value) {
            this.firmbankingRequestId = value;
        }

        Long firmbankingRequestId;
    }

    @Value
    public static class FromBankName {
        public FromBankName(String value) {
            this.nameValue = value;
        }

        String nameValue;
    }

    @Value
    public static class FromBankAccountNumber {
        public FromBankAccountNumber(String value) {
            this.bankAccountNumberValue = value;
        }

        String bankAccountNumberValue;
    }

    @Value
    public static class ToBankName {
        public ToBankName(String value) {
            this.nameValue = value;
        }

        String nameValue;
    }

    @Value
    public static class ToBankAccountNumber {
        public ToBankAccountNumber(String value) {
            this.bankAccountNumberValue = value;
        }

        String bankAccountNumberValue;
    }

    @Value
    public static class MoneyAmount {
        public MoneyAmount(int value) {
            this.moneyAmountValue = value;
        }

        int moneyAmountValue;
    }
}

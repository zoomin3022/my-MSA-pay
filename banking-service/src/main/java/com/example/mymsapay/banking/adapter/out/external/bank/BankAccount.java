package com.example.mymsapay.banking.adapter.out.external.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 외부 은행 시스템에서 조회해 올 계좌의 정보
@Getter
@AllArgsConstructor
public class BankAccount {
    private String bankName;
    private String bankAccountNumber;
    private boolean isValid;
}

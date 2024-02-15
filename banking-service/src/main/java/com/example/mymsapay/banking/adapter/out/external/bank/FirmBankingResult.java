package com.example.mymsapay.banking.adapter.out.external.bank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FirmBankingResult {

    private Result result;

    public static enum Result {
        SUCCESS, FAIL
    }
}

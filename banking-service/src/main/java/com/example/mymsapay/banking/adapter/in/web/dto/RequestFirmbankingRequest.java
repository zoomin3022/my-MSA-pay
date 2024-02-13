package com.example.mymsapay.banking.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestFirmbankingRequest {
    // a -> b 실물 계좌로 요청을 하기 위한 Request

    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;

    private int moneyAmount; // 송금할 금액 (원)
}

package com.example.mymsapay.banking.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBankAccountRequest {
    @Positive
    private Long membershipId;

    @NotBlank
    private String bankName;
    @NotBlank
    private String bankAccountNumber;

    private boolean linkedSatusIsValid;
}

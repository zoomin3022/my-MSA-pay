package com.example.mymsapay.banking.adapter.out.persistence.registeredaccount;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bank_account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisteredBankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankAccountId;

    private Long membershipId;

    private String bankName;

    private String bankAccountNumber;

    private boolean linkedStatusIsValid;

    @Builder
    public RegisteredBankAccountEntity(Long membershipId, String bankName, String bankAccountNumber, boolean linkedStatusIsValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;
    }
}

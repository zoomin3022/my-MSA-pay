package com.example.mymsapay.money.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipStatus {
    private Long membershipId;
    private boolean isValid;
}

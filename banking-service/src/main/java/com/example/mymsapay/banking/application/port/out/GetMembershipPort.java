package com.example.mymsapay.banking.application.port.out;

public interface GetMembershipPort {
    public MembershipStatus getMembership(Long membershipId);
}

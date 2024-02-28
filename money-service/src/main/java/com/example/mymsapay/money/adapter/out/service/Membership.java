package com.example.mymsapay.money.adapter.out.service;

public record Membership(Long membershipId, String name, String email, String address,
                         Boolean isValid, String aggregateIdentifier) {
}

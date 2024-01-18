package com.example.mymsapay.membership.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "membership")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembershipEntity {

    @Id
    @GeneratedValue
    private Long membershipId;

    private String name;

    private String address;

    private String email;

    private boolean isValid;

    private String aggregateIdentifier;

    @Builder
    public MembershipEntity(String name, String address, String email, boolean isValid, String aggregateIdentifier) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.isValid = isValid;
        this.aggregateIdentifier = aggregateIdentifier;
    }
}

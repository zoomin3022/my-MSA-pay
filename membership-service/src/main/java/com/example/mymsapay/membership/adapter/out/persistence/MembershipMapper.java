package com.example.mymsapay.membership.adapter.out.persistence;

import com.example.mymsapay.membership.domain.Membership;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {
    public Membership entityToDomain(
            MembershipEntity membership) {
        return Membership.generateMember(
                new Membership.MembershipId(membership.getMembershipId()),
                new Membership.MembershipName(membership.getName()),
                new Membership.MembershipEmail(membership.getEmail()),
                new Membership.MembershipAddress(membership.getAddress()),
                new Membership.MembershipIsValid(membership.isValid()),
                new Membership.MembershipAggregateIdentifier(membership.getAggregateIdentifier())
        );
    }
}

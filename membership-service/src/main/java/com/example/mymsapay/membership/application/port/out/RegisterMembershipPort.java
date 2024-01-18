package com.example.mymsapay.membership.application.port.out;

import com.example.mymsapay.membership.adapter.out.persistence.MembershipEntity;
import com.example.mymsapay.membership.domain.Membership;

public interface RegisterMembershipPort {

    MembershipEntity createMembership(
              Membership.MembershipName membershipName
            , Membership.MembershipEmail membershipEmail
            , Membership.MembershipAddress membershipAddress
            , Membership.MembershipIsValid membershipIsValid
            , Membership.MembershipAggregateIdentifier membershipAggregateIdentifier
    );
}

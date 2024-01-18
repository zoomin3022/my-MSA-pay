package com.example.mymsapay.membership.application.port.in;

import com.example.mymsapay.membership.domain.Membership;

public interface RegisterMembershipUseCase {
    Membership registerMembership(RegisterMembershipCommand command);
}

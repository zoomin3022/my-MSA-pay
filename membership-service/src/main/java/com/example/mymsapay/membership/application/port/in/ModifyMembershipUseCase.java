package com.example.mymsapay.membership.application.port.in;

import com.example.mymsapay.membership.domain.Membership;

public interface ModifyMembershipUseCase {
    Membership modifyMembership(Long membershipId, ModifyMembershipCommand modifyMembershipCommand);
}

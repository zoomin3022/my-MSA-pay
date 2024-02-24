package com.example.mymsapay.membership.application.service;

import com.example.mymsapay.common.UseCase;
import com.example.mymsapay.membership.adapter.out.persistence.MembershipEntity;
import com.example.mymsapay.membership.adapter.out.persistence.MembershipMapper;
import com.example.mymsapay.membership.application.port.in.RegisterMembershipCommand;
import com.example.mymsapay.membership.application.port.in.RegisterMembershipUseCase;
import com.example.mymsapay.membership.application.port.out.RegisterMembershipPort;
import com.example.mymsapay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class RegisterMembershipService implements RegisterMembershipUseCase {

    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper membershipMapper;

    @Transactional
    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
        MembershipEntity membershipEntity =
        registerMembershipPort.createMembership(
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipAggregateIdentifier("default"));

        return membershipMapper.entityToDomain(membershipEntity);
    }
}

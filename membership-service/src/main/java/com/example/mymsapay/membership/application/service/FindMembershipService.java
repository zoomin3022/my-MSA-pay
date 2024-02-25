package com.example.mymsapay.membership.application.service;

import com.example.mymsapay.common.UseCase;
import com.example.mymsapay.membership.adapter.out.persistence.MembershipEntity;
import com.example.mymsapay.membership.adapter.out.persistence.MembershipMapper;
import com.example.mymsapay.membership.application.port.in.FindMembershipCommand;
import com.example.mymsapay.membership.application.port.in.FindMembershipUseCase;
import com.example.mymsapay.membership.application.port.out.FindMembershipPort;
import com.example.mymsapay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;


import java.util.ArrayList;

@RequiredArgsConstructor
@UseCase
public class FindMembershipService implements FindMembershipUseCase {

    private final FindMembershipPort findMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership findMembership(FindMembershipCommand command) {
        MembershipEntity entity = findMembershipPort.findById(command.getMembershipId());
        return membershipMapper.entityToDomain(entity);
    }
}
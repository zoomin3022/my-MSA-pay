package com.example.mymsapay.membership.application.service;

import com.example.mymsapay.UseCase;
import com.example.mymsapay.membership.adapter.out.persistence.MembershipEntity;
import com.example.mymsapay.membership.adapter.out.persistence.MembershipMapper;
import com.example.mymsapay.membership.application.port.in.ModifyMembershipCommand;
import com.example.mymsapay.membership.application.port.in.ModifyMembershipUseCase;
import com.example.mymsapay.membership.application.port.out.ModifyMembershipPort;
import com.example.mymsapay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class ModifyMembershipService implements ModifyMembershipUseCase {

    private final ModifyMembershipPort modifyMembershipPort;
    private final MembershipMapper membershipMapper;

    @Transactional
    @Override
    public Membership modifyMembership(Long membershipId, ModifyMembershipCommand command) {
        MembershipEntity membershipEntity = modifyMembershipPort.modifyMembership(membershipId,
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()));

        return membershipMapper.entityToDomain(membershipEntity);
    }
}

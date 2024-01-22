package com.example.mymsapay.membership.adapter.out.persistence;

import com.example.mymsapay.PersistenceAdapter;
import com.example.mymsapay.membership.application.port.out.FindMembershipPort;
import com.example.mymsapay.membership.application.port.out.ModifyMembershipPort;
import com.example.mymsapay.membership.application.port.out.RegisterMembershipPort;
import com.example.mymsapay.membership.domain.Membership;
import com.example.mymsapay.membership.exception.MembershipException;
import com.example.mymsapay.membership.exception.MembershipExceptionType;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements FindMembershipPort, RegisterMembershipPort, ModifyMembershipPort {
    private final SpringDataMembershipRepository membershipRepository;

    @Override
    public MembershipEntity findById(Long memberId) {
        MembershipEntity membershipEntity = membershipRepository.findById(memberId)
                .orElseThrow(() -> new MembershipException(MembershipExceptionType.MEMBERSHIP_EXCEPTION_NOT_EXISTS));
        return membershipEntity;
    }

    @Override
    public MembershipEntity createMembership(Membership.MembershipName membershipName
            , Membership.MembershipEmail membershipEmail
            , Membership.MembershipAddress membershipAddress
            , Membership.MembershipIsValid membershipIsValid
            , Membership.MembershipAggregateIdentifier membershipAggregateIdentifier
    ) {
        return membershipRepository.save(
                new MembershipEntity(
                        membershipName.getNameValue(),
                        membershipEmail.getEmailValue(),
                        membershipAddress.getAddressValue(),
                        membershipIsValid.isValidValue(),
                        membershipAggregateIdentifier.getAggregateIdentifier()
                ));
    }

    @Override
    public MembershipEntity modifyMembership(Long membershipId, Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress) {
        MembershipEntity membershipEntity = findById(membershipId);
        membershipEntity.setName(membershipName.getNameValue() != null ? membershipName.getNameValue() : membershipEntity.getName());
        membershipEntity.setEmail(membershipEmail.getEmailValue() != null ? membershipEmail.getEmailValue() : membershipEntity.getEmail());
        membershipEntity.setAddress(membershipAddress.getAddressValue() != null ? membershipAddress.getAddressValue() : membershipEntity.getAddress());

        return membershipEntity;
    }
}

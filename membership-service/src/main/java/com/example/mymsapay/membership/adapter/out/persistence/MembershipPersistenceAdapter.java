package com.example.mymsapay.membership.adapter.out.persistence;

import com.example.mymsapay.PersistenceAdapter;
import com.example.mymsapay.membership.application.port.out.RegisterMembershipPort;
import com.example.mymsapay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort {
    private final SpringDataMembershipRepository membershipRepository;
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
}

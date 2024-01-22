package com.example.mymsapay.membership.application.port.out;

import com.example.mymsapay.membership.adapter.out.persistence.MembershipEntity;

public interface FindMembershipPort {
    MembershipEntity findById(Long memberId);
}

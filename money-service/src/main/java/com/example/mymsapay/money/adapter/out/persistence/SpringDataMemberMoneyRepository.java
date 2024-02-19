package com.example.mymsapay.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataMemberMoneyRepository extends JpaRepository<MemberMoneyEntity, Long> {

    Optional<MemberMoneyEntity> findByMembershipId(Long membershipId);
}

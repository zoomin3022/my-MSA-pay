package com.example.mymsapay.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMoneyChargingRepository extends JpaRepository<MoneyChargingEntity, Long> {
}

package com.example.mymsapay.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataBankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
}

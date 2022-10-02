package com.example.ebankservice.repository;

import com.example.ebankservice.entities.BankAccount;
import com.example.ebankservice.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
    List<BankAccount> findByCurrency(String currency);
    //List<BankAccount> findByBankAccountType(AccountType accountType);
}

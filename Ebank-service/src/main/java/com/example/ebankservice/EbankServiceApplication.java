package com.example.ebankservice;

import com.example.ebankservice.entities.BankAccount;
import com.example.ebankservice.enums.AccountType;
import com.example.ebankservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class EbankServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository) {
        return args -> {
            for(int i =0; i<1000;i++) {
                BankAccount bankAccount = BankAccount.builder()
                        .id(UUID.randomUUID().toString())
                        .accountType(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.CURRENT_ACCOUNT)
                        .balance(10000+Math.random()*900000)
                        .createdAt(new Date())
                        .currency("XFA")
                        .build();
                bankAccountRepository.save(bankAccount);
            }
        };
    }
}

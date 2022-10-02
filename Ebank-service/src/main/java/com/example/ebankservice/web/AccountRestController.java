package com.example.ebankservice.web;

import com.example.ebankservice.entities.BankAccount;
import com.example.ebankservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AccountRestController {

    public AccountRestController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    private BankAccountRepository bankAccountRepository;

    @GetMapping("/allBankAccounts")
    public List<BankAccount> allBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return bankAccounts;
    }

    @GetMapping("/bankAccount/{id}")
    public BankAccount bankAccountDetail(@PathVariable String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account Bank is not fount",id)));

    }

    @PostMapping("/addBankAccount")
    public BankAccount addBankAccount(@RequestBody BankAccount bankAccount){
        bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAccount);
    }

    @PutMapping("/updateBankAccount/{id}")
    public BankAccount updateBankAccount(@PathVariable String id,@RequestBody BankAccount bankAccount) {
        BankAccount account =  bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Acccount Not Found")));
        account.setBalance(bankAccount.getBalance());
        account.setAccountType(bankAccount.getAccountType());
        account.setCreatedAt(bankAccount.getCreatedAt());
        account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/deleteBankAccount/{id}")
    public void deleteBankAccount(@PathVariable String id) {
         bankAccountRepository.deleteById(id);
    }

}

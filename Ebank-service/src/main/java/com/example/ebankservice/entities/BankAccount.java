package com.example.ebankservice.entities;

import com.example.ebankservice.enums.AccountType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data 
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
}

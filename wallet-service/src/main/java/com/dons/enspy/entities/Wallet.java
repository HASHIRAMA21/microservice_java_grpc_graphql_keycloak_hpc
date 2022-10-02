package com.dons.enspy.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class Wallet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Double balance;
    private Long createAt;
    private String userId;
    @ManyToOne()
    private Currency currency;
    @OneToMany(mappedBy="wallet")
    private List<WalletTransaction> walletTransactions;
}

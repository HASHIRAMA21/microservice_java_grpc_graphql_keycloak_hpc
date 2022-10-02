package com.dons.enspy.entities;

import com.dons.enspy.enums.TransactionType;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor @ToString
@Builder
public class WalletTransaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long timestamp;
    private Double amount;
    @ManyToOne()
    private Wallet wallet;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}

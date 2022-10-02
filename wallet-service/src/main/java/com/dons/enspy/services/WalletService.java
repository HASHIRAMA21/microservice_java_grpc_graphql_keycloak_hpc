package com.dons.enspy.services;

import com.dons.enspy.enums.TransactionType;
import com.dons.enspy.repositories.CurrencyRepository;
import com.dons.enspy.repositories.WalletRepository;
import com.dons.enspy.repositories.WalletTransactionRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dons.enspy.entities.*;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@Transactional
public class WalletService {

    private CurrencyRepository currencyRepository;;
    private WalletRepository walletRepository;
    private WalletTransactionRepository walletTransactionRepository;

    public WalletService(CurrencyRepository currencyRepository, WalletRepository walletRepository, WalletTransactionRepository walletTransactionRepository) {
        this.currencyRepository = currencyRepository;
        this.walletRepository = walletRepository;
        this.walletTransactionRepository = walletTransactionRepository;
    }

    public void loadData() throws IOException {
        URI uri = new ClassPathResource("currencies_data.csv").getURI();
        Path path = Paths.get(uri);
        List<String> lines = Files.readAllLines(path);
        for(int i =1; i< lines.size();  i++) {
            String[] line = lines.get(i).split(";");
            Currency currency = Currency.builder()
                    .code(line[0])
                    .name(line[1])
                    .salePrice(Double.parseDouble(line[3]))
                    .build();
            currencyRepository.save(currency);
        }
        Stream.of("HAD","USD","EUR","CAD","XFA").forEach(currencyCode -> {
            Currency currency = currencyRepository.findById(currencyCode)
                    .orElseThrow(()->new RuntimeException(String.format("Currency not found",currencyCode)));
            Wallet wallet = new Wallet();
            wallet.setBalance(10000.0);
            wallet.setCurrency(currency);
            wallet.setCreateAt(System.currentTimeMillis());
            wallet.setUserId("user1");
            wallet.setId(UUID.randomUUID().toString());
            walletRepository.save(wallet);
        });
        walletRepository.findAll().forEach(wallet->{
             for(int i = 0; i< 1000; i++){

                 WalletTransaction walletTransaction = WalletTransaction.builder()
                         .amount(Math.random()*10000)
                         .wallet(wallet)
                         .transactionType(Math.random() > 0.5 ? TransactionType.DEBIT:TransactionType.CREDIT)
                         .build();
                 walletTransactionRepository.save(walletTransaction);
             }
        });
    }
}

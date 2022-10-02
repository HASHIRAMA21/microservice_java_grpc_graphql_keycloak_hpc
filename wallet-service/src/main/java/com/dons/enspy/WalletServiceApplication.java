package com.dons.enspy;

import com.dons.enspy.services.WalletService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WalletServiceApplication implements CommandLineRunner {

    private WalletService walletService;

    public WalletServiceApplication(WalletService walletService) {
        this.walletService = walletService;
    }

    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        walletService.loadData();
    }
}

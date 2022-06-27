package org.quickfix.client.marketdata.lmax;

import io.allune.quickfixj.spring.boot.starter.EnableQuickFixJClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@EnableQuickFixJClient
@SpringBootApplication
public class QuickfixLmaxMarketDataApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(QuickfixLmaxMarketDataApplication.class, args);
    }

}

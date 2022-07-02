package org.quickfix.client.marketdata.lmax.client.api;

import lombok.RequiredArgsConstructor;
import org.quickfix.client.marketdata.lmax.client.QuickfixMarketDataConfig;
import org.quickfix.core.components.model.fix.FixMessageLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class MarketDataBeans {

    @Autowired
    private final QuickfixMarketDataConfig quickfixMarketDataConfig;

    @Bean
    public FixMessageLib getFixMessageLib() {
        return new FixMessageLib(quickfixMarketDataConfig);
    }
}

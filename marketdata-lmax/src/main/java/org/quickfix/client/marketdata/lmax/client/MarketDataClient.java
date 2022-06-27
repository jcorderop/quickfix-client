package org.quickfix.client.marketdata.lmax.client;

import org.quickfix.core.components.adaptar.QuickfixClientAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MarketDataClient extends QuickfixClientAbstract {
    private final QuickfixMarketDataConfig quickfixMarketDataConfig;

    @Autowired
    public MarketDataClient(QuickfixMarketDataConfig quickfixMarketDataConfig) {
        super(quickfixMarketDataConfig);
        this.quickfixMarketDataConfig = quickfixMarketDataConfig;
    }
}

package org.quickfix.client.execution.lmax.client;

import org.quickfix.client.common.adaptar.QuickfixClientAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ExecutionClient extends QuickfixClientAbstract {
    private final QuickfixExecutionConfig quickfixMarketDataConfig;

    @Autowired
    public ExecutionClient(QuickfixExecutionConfig quickfixMarketDataConfig) {
        super(quickfixMarketDataConfig);
        this.quickfixMarketDataConfig = quickfixMarketDataConfig;
    }
}

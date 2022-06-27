package org.quickfix.client.marketdata.lmax.client;

import org.quickfix.client.common.client.QuickfixConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "quickfixj.client")
public class QuickfixMarketDataConfig extends QuickfixConfig {
}

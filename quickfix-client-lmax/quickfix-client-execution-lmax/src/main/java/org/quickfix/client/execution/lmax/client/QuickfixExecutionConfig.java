package org.quickfix.client.execution.lmax.client;

import org.quickfix.client.common.adaptar.QuickfixConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "quickfixj.client")
public class QuickfixExecutionConfig extends QuickfixConfig {
}

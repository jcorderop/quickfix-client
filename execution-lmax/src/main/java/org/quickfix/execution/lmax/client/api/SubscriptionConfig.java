package org.quickfix.execution.lmax.client.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/*
  https://www.baeldung.com/configuration-properties-in-spring-boot
 */

@Configuration
@ConfigurationProperties(prefix = "subscriptions")
@Setter
@Getter
@ToString
public class SubscriptionConfig {
  private Map<String, String> tickers;
}

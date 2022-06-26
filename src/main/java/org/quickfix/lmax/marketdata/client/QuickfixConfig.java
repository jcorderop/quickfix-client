package org.quickfix.lmax.marketdata.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "quickfixj.client")
@Setter
@Getter
@ToString
public class QuickfixConfig {
    private String senderCompID;
    private String targetCompID;

    private String dataDictionaryFile;

    @Value("#{systemEnvironment['FIX_USER_NAME'] ?: 'default_value'}")
    private String userName;
    @Value("#{systemEnvironment['FIX_PASSWORD'] ?: 'default_value'}")
    private String password;
}

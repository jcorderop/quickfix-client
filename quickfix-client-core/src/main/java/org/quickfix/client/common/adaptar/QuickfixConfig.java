package org.quickfix.client.common.adaptar;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Setter
@Getter
@ToString
public class QuickfixConfig {
    private String targetCompID;

    private String dataDictionaryFile;

    @Value("#{systemEnvironment['FIX_SENDER_COMP_ID'] ?: 'default_value'}")
    private String senderCompID;

    @Value("#{systemEnvironment['FIX_USER_NAME'] ?: 'default_value'}")
    private String userName;
    @Value("#{systemEnvironment['FIX_PASSWORD'] ?: 'default_value'}")
    private String password;
}

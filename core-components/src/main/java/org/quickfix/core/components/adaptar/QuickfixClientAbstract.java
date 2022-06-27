package org.quickfix.core.components.adaptar;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import quickfix.*;
import quickfix.fix44.MessageCracker;

@Component
public abstract class QuickfixClientAbstract {

    private final QuickfixConfig quickfixConfig;

    protected QuickfixClientAbstract(QuickfixConfig quickfixConfig) {
        this.quickfixConfig = quickfixConfig;
    }

    @Bean
    public Application clientApplication(MessageCracker messageCracker) {
        return new ClientApplicationAdapter(messageCracker, quickfixConfig);
    }

    @Bean
    public MessageCracker messageCracker() throws ConfigError {
        return new ApplicationMessageCracker(new DataDictionary(quickfixConfig.getDataDictionaryFile()));
    }

    @Bean
    public Initiator clientInitiator(Application clientApplication, MessageStoreFactory clientMessageStoreFactory,
                                     SessionSettings clientSessionSettings, LogFactory clientLogFactory,
                                     MessageFactory clientMessageFactory) throws ConfigError {

        return new ThreadedSocketInitiator(clientApplication, clientMessageStoreFactory, clientSessionSettings,
                clientLogFactory, clientMessageFactory);
    }

    @Bean
    public LogFactory clientLogFactory(SessionSettings clientSessionSettings) {
        return new FileLogFactory(clientSessionSettings);
    }
}

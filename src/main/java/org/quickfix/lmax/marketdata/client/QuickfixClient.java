package org.quickfix.lmax.marketdata.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import quickfix.*;
import quickfix.fix44.MessageCracker;

@Component
public class QuickfixClient {

    @Autowired
    private QuickfixConfig quickfixConfig;

    @Bean
    public Application clientApplication(MessageCracker messageCracker) {
        return new ClientApplicationAdapter(messageCracker, quickfixConfig);
    }

    @Bean
    public MessageCracker messageCracker() {
        try {
            return new ApplicationMessageCracker(new DataDictionary(quickfixConfig.getDataDictionaryFile()));
        } catch (ConfigError e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public Initiator clientInitiator(quickfix.Application clientApplication, MessageStoreFactory clientMessageStoreFactory,
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

package org.quickfix.execution.lmax.client.api;

import lombok.RequiredArgsConstructor;
import org.quickfix.core.components.model.fix.FixMessageLib;
import org.quickfix.execution.lmax.client.QuickfixExecutionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ExecutionBeans {

    @Autowired
    private final QuickfixExecutionConfig quickfixExecutionConfig;

    @Bean
    public FixMessageLib getFixMessageLib() {
        return new FixMessageLib(quickfixExecutionConfig);
    }
}

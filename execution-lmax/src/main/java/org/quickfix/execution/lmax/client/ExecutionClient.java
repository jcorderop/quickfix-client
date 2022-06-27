package org.quickfix.execution.lmax.client;

import org.quickfix.core.components.adaptar.QuickfixClientAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ExecutionClient extends QuickfixClientAbstract {

    private final QuickfixExecutionConfig quickfixConfig;

    @Autowired
    public ExecutionClient(QuickfixExecutionConfig quickfixConfig) {
        super(quickfixConfig);
        this.quickfixConfig = quickfixConfig;
    }
}

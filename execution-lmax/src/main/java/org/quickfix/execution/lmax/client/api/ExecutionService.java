package org.quickfix.execution.lmax.client.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quickfix.core.components.model.fix.FixMessageLib;
import org.quickfix.core.components.model.model.Order;
import org.springframework.stereotype.Service;
import quickfix.Session;
import quickfix.SessionNotFound;
import quickfix.field.*;
import quickfix.field.Side;
import quickfix.fix44.NewOrderSingle;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Slf4j
@Service
public class ExecutionService {

    private final FixMessageLib fixMessageLib;

    private final SubscriptionConfig subscriptionConfig;

    @PostConstruct
    void ConfigurationLoaded() {
        log.info("subscriptionConfig: {}", subscriptionConfig);
        log.info("quickfixConfig: {}", fixMessageLib.getQuickfixConfig());
    }

    public boolean newOrderSingle(final Order order) throws SessionNotFound  {
        final NewOrderSingle newOrderSingle = createNewOrderSingle(order);
        fixMessageLib.createHeader(newOrderSingle);
        return Session.sendToTarget(newOrderSingle);
    }

    private NewOrderSingle createNewOrderSingle(final Order order) {
        final NewOrderSingle newOrderSingle = new NewOrderSingle(new ClOrdID(order.orderId()),
                new Side(order.side().getSide()),
                new TransactTime(order.orderTimestamp()),
                new OrdType(order.orderType().getType()));
        newOrderSingle.set(new SecurityID(subscriptionConfig.getSecuritylId(order.ticker())));
        newOrderSingle.set(new SecurityIDSource(FixMessageLib.SECURITY_ID_SOURCE_EXCHSYMB));
        newOrderSingle.set(new TimeInForce(TimeInForce.IMMEDIATE_OR_CANCEL));
        newOrderSingle.set(fixMessageLib.createOrderQtyData(order.amount()));
        return newOrderSingle;
    }




}

package org.quickfix.core.components.model.model;

import java.time.LocalDateTime;
import java.util.Objects;

public record Order(String refId,
                    String ticker,
                    Side side,
                    LocalDateTime requestTimestamp,
                    OrderType orderType,
                    Double amount,
                    String orderId,
                    LocalDateTime orderTimestamp,
                    Double limitPrice) {
    public Order {
        Objects.requireNonNull(refId);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(ticker);
        Objects.requireNonNull(side);
        Objects.requireNonNull(orderType);
        Objects.requireNonNull(requestTimestamp);
        if (!Objects.equals(OrderType.MARKET_ORDER, orderType)) {
            Objects.requireNonNull(limitPrice);
        }
        orderId = String.valueOf(System.nanoTime());
        orderTimestamp = LocalDateTime.now();
    }
}

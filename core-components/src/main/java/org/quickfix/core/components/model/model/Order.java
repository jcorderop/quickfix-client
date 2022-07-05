package org.quickfix.core.components.model.model;

import com.fasterxml.jackson.annotation.JsonFormat;

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
        Objects.requireNonNull(ticker);
        Objects.requireNonNull(side);
        Objects.requireNonNull(requestTimestamp);
        Objects.requireNonNull(orderType);
        Objects.requireNonNull(amount);
        if (!Objects.equals(OrderType.MARKET, orderType)) {
            Objects.requireNonNull(limitPrice);
        }
        orderId = String.valueOf(System.nanoTime());
        orderTimestamp = LocalDateTime.now();
    }
}

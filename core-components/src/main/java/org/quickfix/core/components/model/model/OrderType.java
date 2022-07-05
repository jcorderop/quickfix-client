package org.quickfix.core.components.model.model;

import lombok.Getter;
import quickfix.field.OrdType;

@Getter
public enum OrderType {
    MARKET(OrdType.MARKET), LIMIT(OrdType.LIMIT);

    private final char type;

    OrderType(char type) {
        this.type = type;
    }
}

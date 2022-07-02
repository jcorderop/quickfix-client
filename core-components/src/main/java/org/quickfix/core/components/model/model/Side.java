package org.quickfix.core.components.model.model;

import lombok.Getter;

@Getter
public enum Side {
    BUY(quickfix.field.Side.BUY), SELl(quickfix.field.Side.SELL);

    private final char side;

    Side(char side) {
        this.side = side;
    }
}

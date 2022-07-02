package org.quickfix.core.components.model.fix;

import lombok.Getter;
import org.quickfix.core.components.adaptar.QuickfixConfig;
import quickfix.field.OrderQty;
import quickfix.field.SenderCompID;
import quickfix.field.TargetCompID;
import quickfix.fix44.Message;
import quickfix.fix44.component.OrderQtyData;

@Getter
public class FixMessageLib {

    public static final String SECURITY_ID_SOURCE_EXCHSYMB = "8";

    private final QuickfixConfig quickfixConfig;


    public FixMessageLib(QuickfixConfig quickfixConfig) {
        this.quickfixConfig = quickfixConfig;
    }

    public void createHeader(final Message message) {
        final quickfix.Message.Header header = message.getHeader();
        header.setField(new SenderCompID(quickfixConfig.getSenderCompID()));
        header.setField(new TargetCompID(quickfixConfig.getTargetCompID()));
    }

    public OrderQtyData createOrderQtyData(final Double quantity) {
        final OrderQtyData orderQtyData = new OrderQtyData();
        orderQtyData.set(new OrderQty(quantity));
        return orderQtyData;
    }

}

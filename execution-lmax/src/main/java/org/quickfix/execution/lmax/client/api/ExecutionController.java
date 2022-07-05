package org.quickfix.execution.lmax.client.api;

import lombok.extern.slf4j.Slf4j;
import org.quickfix.core.components.model.model.Order;
import org.quickfix.core.components.model.rest.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quickfix.SessionNotFound;

import java.text.MessageFormat;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/order")
public class ExecutionController {

    @Autowired
    ExecutionService executionService;

    @PostMapping(path = "/new")
    public ResponseEntity<BaseResponse> executeOrder(@RequestBody Order order) {
        log.info("New SingleOrder: {}",order);
        return createNewOrderSingle(order);
    }

    private ResponseEntity<BaseResponse> createNewOrderSingle(final Order order) {
        try {
            if (executionService.newOrderSingle(order)) {
                return new ResponseEntity<>(new BaseResponse("Order ["+order.orderId()+"] accepted!"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new BaseResponse("Unexpected error ["+order.orderId()+"] was NOT accepted!"), HttpStatus.OK);
            }
        } catch (SessionNotFound e) {
            e.printStackTrace();
            return new ResponseEntity<>(new BaseResponse(MessageFormat.format("Error while placing order - {0}",
                    order.orderId())),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

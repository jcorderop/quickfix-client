package org.quickfix.client.execution.lmax.client.api;

import lombok.extern.slf4j.Slf4j;
import org.quickfix.client.common.model.rest.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quickfix.SessionNotFound;

import java.text.MessageFormat;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/marketdata")
public class ExecutionController {

    @Autowired
    ExecutionService marketDataService;

    @PostMapping(path = "/subscribe/{ticker}")
    public ResponseEntity<BaseResponse> subscribe(@PathVariable("ticker") String ticker) {
        return subscription(ticker, true);
    }

    @PostMapping(path = "/unsubscribe/{ticker}")
    public ResponseEntity<BaseResponse> unsubscribe(@PathVariable("ticker") String ticker) {
        return subscription(ticker, false);
    }

    private ResponseEntity<BaseResponse> subscription(String ticker, boolean subscribe) {
        try {
            if (marketDataService.subscribe(ticker, subscribe)) {
                if (subscribe) {
                    return new ResponseEntity<>(new BaseResponse("subscribed ["+ticker+"] accepted!"), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new BaseResponse("Unsubscribed ["+ticker+"] accepted!"), HttpStatus.OK);
                }

            } else {
                return new ResponseEntity<>(new BaseResponse(MessageFormat.format("Invalid request - {0}", ticker)), HttpStatus.BAD_REQUEST);
            }
        } catch (SessionNotFound e) {
            e.printStackTrace();
            return new ResponseEntity<>(new BaseResponse(MessageFormat.format("Error while subscribing - {0}", ticker)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

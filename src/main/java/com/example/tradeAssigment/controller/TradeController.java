package com.example.tradeAssigment.controller;

import com.example.tradeAssigment.exception.InvalidTradeException;
import com.example.tradeAssigment.model.Trade;
import com.example.tradeAssigment.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeController {

    @Autowired
    TradeService tradeService;

    @PostMapping("/trades")
    public ResponseEntity<String> tradeValidateStore(@RequestBody Trade trade) {
        if (tradeService.isValid(trade)) {
            tradeService.save(trade);
        } else {
            throw new InvalidTradeException(trade, "Trade details not found");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/trades")
    public List<Trade> findAllTrades() {
        return tradeService.findAll();
    }

}

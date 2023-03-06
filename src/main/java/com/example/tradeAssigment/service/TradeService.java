package com.example.tradeAssigment.service;

import com.example.tradeAssigment.model.Trade;

import java.util.List;

public interface TradeService {
    public boolean isValid(Trade trade);

    public void save(Trade trade);

    public List<Trade> findAll();

    void expireTrade();

}

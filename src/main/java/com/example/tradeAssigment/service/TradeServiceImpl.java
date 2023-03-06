package com.example.tradeAssigment.service;

import com.example.tradeAssigment.model.Trade;
import com.example.tradeAssigment.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public boolean isValid(Trade trade) {
        if (validateMaturityDate(trade)) {
            Optional<Trade> existingTrade = tradeRepository.findById(trade.getTradeId());
            return existingTrade.map(value -> validateVersion(trade, value)).orElse(true);
        }
        return false;
    }

    private boolean validateVersion(Trade trade, Trade trade1) {
        if (trade.getVersion() >= trade1.getVersion()) {
            return true;
        }
        return false;
    }

    private boolean validateMaturityDate(Trade trade) {
        return !trade.getMaturityDate().isBefore(LocalDate.now());
    }

    @Override
    public void save(Trade trade) {
        trade.setCreatedDate(LocalDate.now());
        tradeRepository.save(trade);
    }

    @Override
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    @Override
    public void expireTrade() {
        tradeRepository.findAll().stream().forEach(t -> {
            if (!validateMaturityDate(t)) {
                t.setExpiredFlag("Y");
                tradeRepository.save(t);
            }
        });
    }


}

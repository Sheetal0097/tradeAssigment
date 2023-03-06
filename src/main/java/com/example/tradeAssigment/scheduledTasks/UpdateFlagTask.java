package com.example.tradeAssigment.scheduledTasks;

import com.example.tradeAssigment.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UpdateFlagTask {
    private static final Logger logger = LoggerFactory.getLogger(UpdateFlagTask.class);

    @Autowired
    private TradeService tradeService;

    //@Scheduled(cron = "${trade.expiry.schedule}")
    @Scheduled(cron = "0/30 * * * * *")
    public void expireFlagTask() {
        logger.info("Task is triggred at {}", new Date());
        tradeService.expireTrade();
    }

}

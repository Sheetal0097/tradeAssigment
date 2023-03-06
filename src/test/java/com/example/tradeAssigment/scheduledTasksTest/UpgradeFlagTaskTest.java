package com.example.tradeAssigment.scheduledTasksTest;

import com.example.tradeAssigment.TradeAssigmentApplication;
import com.example.tradeAssigment.scheduledTasks.UpdateFlagTask;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Durations.ONE_MINUTE;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig(TradeAssigmentApplication.class)
@ExtendWith(SpringExtension.class)
public class UpgradeFlagTaskTest {
    @SpyBean
    private UpdateFlagTask updateFlagTask;

    @Test
    void    testScehduledTask() {
        await().atMost(ONE_MINUTE).untilAsserted(() -> verify(updateFlagTask, atLeast(2)).expireFlagTask());
    }
}

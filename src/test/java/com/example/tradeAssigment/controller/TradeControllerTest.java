package com.example.tradeAssigment.controller;

import com.example.tradeAssigment.exception.InvalidTradeException;
import com.example.tradeAssigment.model.Trade;
import com.example.tradeAssigment.service.TradeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TradeControllerTest {
    @InjectMocks
    private TradeController controller;

    @Mock
    private TradeService tradeService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testStoreTrade_success() {
        Trade trade = new Trade("T6", 2, "CP-1", "B1",
                LocalDate.of(2022, 5, 20), LocalDate.now(), "N");
        Mockito.when(tradeService.isValid(trade)).thenReturn(true);
        Mockito.doNothing().when(tradeService).save(trade);
        ResponseEntity<String> status = controller.tradeValidateStore(trade);

        assertSame(HttpStatus.OK, status.getStatusCode());
    }

    @Test
    void testStoreTrade_InvalidTradeException() {
        Trade trade = new Trade("T6", 2, "CP-1", "B1",
                LocalDate.of(2022, 5, 20), LocalDate.now(), "N");
        Mockito.when(tradeService.isValid(trade)).thenReturn(false);
        // Mockito.doNothing().when(tradeService).save(trade);
        InvalidTradeException thrown = Assertions.assertThrows(InvalidTradeException.class, () -> {
            controller.tradeValidateStore(trade);
        });

        assertSame("Trade details not found", thrown.getMessage());
    }

}

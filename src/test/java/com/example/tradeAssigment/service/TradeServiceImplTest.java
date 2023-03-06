package com.example.tradeAssigment.service;

import com.example.tradeAssigment.model.Trade;
import com.example.tradeAssigment.repository.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TradeServiceImplTest {
    @InjectMocks
    private TradeServiceImpl tradeService;
    @Mock
    private TradeRepository tradeRepository;

    private Trade trade ;

    @BeforeEach
    void setup()
    {
        trade = new Trade("T6", 1, "CP-1", "B1", LocalDate.of(2022, 5, 20), LocalDate.now(), "N");

    }

    @Test
    void testIsValid() {
        Boolean status = tradeService
                .isValid(new Trade("T7", 1, "CP-1", "B1", LocalDate.of(2023, 5, 20), LocalDate.now(), "N"));
        assertTrue(status);
    }

    @Test
    void testPersist() {
        Mockito.when(tradeRepository.save(any())).thenReturn(trade);
        tradeService.save(trade);
        Mockito.verify(tradeRepository, Mockito.times(1)).save(any());

    }

    @Test
    void testFindAll() {
        Mockito.when(tradeRepository.findAll()).thenReturn(List.of(trade));
        assertTrue(tradeService.findAll().size() > 0);
    }

    @Test
    void testExpireTrade() {
        Mockito.when(tradeRepository.findAll()).thenReturn(List.of(trade));
        Mockito.when(tradeRepository.save(any())).thenReturn(trade);

        try {
            tradeService.expireTrade();
        } catch (Exception e) {
            fail("Failed to excute expire task");
        }
    }

}

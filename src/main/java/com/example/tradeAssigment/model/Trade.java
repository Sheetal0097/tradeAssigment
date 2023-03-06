package com.example.tradeAssigment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Trade")
public class Trade {
    @Id
    private String tradeId;

    private int version;

    private String counterParty;

    private String bookId;

    private LocalDate maturityDate;

    private LocalDate createdDate;

    private String expiredFlag;

}

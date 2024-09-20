package com.example.market_api.data.Inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class InventoryView {
    private Long quantity;
    private LocalDateTime dateArrived;
    private LocalDateTime expirationDate;
    private LocalDateTime lastUpdated;
}

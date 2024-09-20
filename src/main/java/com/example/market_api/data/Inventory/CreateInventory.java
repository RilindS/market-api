package com.example.market_api.data.Inventory;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateInventory {
    private Long quantity;
    private LocalDateTime datearrived;
    private LocalDateTime expirationDate;
    private LocalDateTime lastUpdated;
}

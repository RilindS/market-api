package com.example.market_api.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "inventory")
public class Inventory extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private LocalDateTime lastUpdated;

}

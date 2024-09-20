package com.example.market_api.entity;


import jakarta.persistence.*;
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

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "date_arrived")
    private LocalDateTime dateArrived;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

}

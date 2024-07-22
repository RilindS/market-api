package com.example.market_api.data.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductView {
    private Long id;
    private String name;
    private String description;
    private double price;
}

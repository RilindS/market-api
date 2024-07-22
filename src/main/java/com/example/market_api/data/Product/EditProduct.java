package com.example.market_api.data.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditProduct {
    private int id;
    private String name;
    private double price;
    private String description;
}

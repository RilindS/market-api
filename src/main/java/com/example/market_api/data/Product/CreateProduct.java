package com.example.market_api.data.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProduct {

    private String name;
    private String description;
    private double price;
    private List<Long> supplierIds;
    private List<Long> categoryIds;

}

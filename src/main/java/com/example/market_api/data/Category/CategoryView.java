package com.example.market_api.data.Category;

import jakarta.persistence.NamedQueries;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryView {
    private Long id;
    private String name;
    private String description;
}

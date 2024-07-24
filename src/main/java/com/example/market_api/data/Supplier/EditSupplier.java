package com.example.market_api.data.Supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditSupplier {
    private String name;
    private String contactInfo;
    private String address;
    private String phone;
    private String email;
}

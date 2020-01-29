package com.myshop.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductServiceModel {
    private String id;

    private String name;

    private CategoryServiceModel category;

    private String imageUrl;

    private BigDecimal price;
}

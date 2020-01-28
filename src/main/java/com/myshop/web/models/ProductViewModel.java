package com.myshop.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductViewModel {

    private String id;

    private String name;

    private BigDecimal price;

    private String description;

    private CategoryFormModel category;

    private String imageUrl;
}

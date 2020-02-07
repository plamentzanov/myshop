package com.myshop.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCreateModel {

    private Integer quantity;
    private String username;
    private ProductViewModel product;
}

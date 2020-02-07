package com.myshop.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderViewModel {

    private String id;
    private ProductViewModel product;
    private Integer quantity;
}

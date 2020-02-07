package com.myshop.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderServiceModel {

    private String id;
    private ProductServiceModel product;
    private Integer quantity;
    private UserServiceModel user;
}

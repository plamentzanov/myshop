package com.myshop.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryServiceModel {

    private String id;
    private String name;
    private String imageUrl;
    private List<ProductServiceModel> products;
}

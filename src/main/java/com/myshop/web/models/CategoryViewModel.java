package com.myshop.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryViewModel {

    private String id;
    private String name;
    private String imageUrl;
}

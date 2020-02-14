package com.myshop.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArchivedOrderServiceModel {
    private String productName;
    private Integer quantity;
    private boolean isCompleted;
    private GlobalOrderServiceModel globalOrder;
}

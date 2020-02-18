package com.myshop.web.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArchivedOrderViewModel {

    private String id;
    private String productName;
    private Integer quantity;
    private boolean isCompleted;
}

package com.myshop.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GlobalOrderServiceModel {
    private List<ArchivedOrderServiceModel> orders;
    private UserServiceModel user;
    private Date orderDate;
}

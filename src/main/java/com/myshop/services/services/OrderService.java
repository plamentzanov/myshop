package com.myshop.services.services;

import com.myshop.services.models.OrderServiceModel;

import java.util.List;

public interface OrderService {
    void add(OrderServiceModel order, String username);
    void deleteById(String id);
    Double getTotalSum(String name);
    List<OrderServiceModel> getCart(String username);
}

package com.myshop.services.services;

import com.myshop.services.models.OrderServiceModel;

import java.util.List;

public interface ArchivedOrderService {
    void archive(List<OrderServiceModel> orders, String globalOrderId) throws InterruptedException;
}

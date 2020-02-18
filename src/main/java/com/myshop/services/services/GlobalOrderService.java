package com.myshop.services.services;

import com.myshop.services.models.GlobalOrderServiceModel;

import java.util.List;

public interface GlobalOrderService {
    String create(String username);

    List<GlobalOrderServiceModel> getAll();

    GlobalOrderServiceModel getById(String id);

    void update(GlobalOrderServiceModel globalOrder);

    void deleteById(String id);
}

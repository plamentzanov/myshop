package com.myshop.services.services;

import com.myshop.services.models.ArchivedOrderServiceModel;
import com.myshop.services.models.OrderServiceModel;
import com.myshop.services.models.UserServiceModel;

import java.util.List;

public interface GlobalOrderService {
    String create(String username);
}

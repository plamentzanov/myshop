package com.myshop.services.services;

import com.myshop.data.entities.Product;
import com.myshop.services.models.ProductServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    void add(ProductServiceModel model);
    List<ProductServiceModel> getAll();
}

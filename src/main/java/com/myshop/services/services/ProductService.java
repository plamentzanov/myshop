package com.myshop.services.services;

import com.myshop.data.entities.Product;
import com.myshop.services.models.ProductServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    void add(ProductServiceModel model);
    void update(ProductServiceModel model, String productId);
    void delete(String id);
    List<ProductServiceModel> getAll();
    ProductServiceModel getById(String id);
}

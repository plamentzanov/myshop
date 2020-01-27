package com.myshop.services.services.implementations;

import com.myshop.data.entities.Product;
import com.myshop.data.repositories.CategoryRepository;
import com.myshop.data.repositories.ProductRepository;
import com.myshop.services.models.ProductServiceModel;
import com.myshop.services.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(ProductServiceModel model) {
        Product product = this.modelMapper.map(model, Product.class);
        product.setCategory(this.categoryRepository.findByName(model.getCategoryModel().getName()));
        this.productRepository.saveAndFlush(product);
    }
}

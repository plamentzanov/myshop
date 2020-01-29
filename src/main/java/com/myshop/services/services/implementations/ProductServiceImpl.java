package com.myshop.services.services.implementations;

import com.myshop.data.entities.Product;
import com.myshop.data.repositories.CategoryRepository;
import com.myshop.data.repositories.ProductRepository;
import com.myshop.services.models.ProductServiceModel;
import com.myshop.services.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        product.setCategory(this.categoryRepository.findByName(model.getCategory().getName()));
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public void update(ProductServiceModel model, String productId) {
        Product product = this.modelMapper.map(model, Product.class);
        product.setId(productId);
        product.setCategory(this.categoryRepository.findByName(model.getCategory().getName()));

        if (product.getImageUrl() == null) {
            product.setImageUrl(this.productRepository.findById(productId).get().getImageUrl());
        }

        this.productRepository.saveAndFlush(product);
    }

    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public List<ProductServiceModel> getAll() {
        return this.productRepository.findAll()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel getById(String id) {
        Optional<Product> product = this.productRepository.findById(id);
        return product.map(p -> this.modelMapper.map(p, ProductServiceModel.class)).orElse(null);

    }
}

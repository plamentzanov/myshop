package com.myshop.services.services.implementations;

import com.myshop.data.repositories.ProductRepository;
import com.myshop.services.models.CategoryServiceModel;
import com.myshop.services.models.ProductServiceModel;
import com.myshop.services.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;


class ProductServiceTest extends BaseTest{

    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Test
    public void add_shouldBeSuccessful() {
        CategoryServiceModel category = new CategoryServiceModel();
        category.setImageUrl("www.test.com");
        category.setName("TestCategory");
        ProductServiceModel product = new ProductServiceModel();
        product.setImageUrl("www.test.com");
        product.setCategory(category);
        product.setName("TestProduct");

        this.productService.add(product);
    }

    @Test
    public void add_shouldReturnError() {
        Assertions.assertThrows(Exception.class, () -> this.productService.add(null));
    }

}
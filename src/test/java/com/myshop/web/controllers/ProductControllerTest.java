package com.myshop.web.controllers;


import com.myshop.data.repositories.ProductRepository;
import com.myshop.services.services.implementations.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
public class ProductControllerTest extends BaseTest {

    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductController productController;

    @Autowired
    MockMvc mockMvc;

//    @Test
//    public void getCreate_whenUserIs() throws Exception {
//        this.mockMvc.perform(get("/products/create"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("error"));
//    }

}

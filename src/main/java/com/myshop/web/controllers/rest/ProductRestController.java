package com.myshop.web.controllers.rest;

import com.myshop.services.services.ProductService;
import com.myshop.web.models.ProductViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductRestController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public List<ProductViewModel> getAllProducts(){
        return this.productService.getAll()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/all/{id}")
    public List<ProductViewModel> getByCategory(@PathVariable String id){
        return this.productService.getAllByCategoryId(id)
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList());
    }
}

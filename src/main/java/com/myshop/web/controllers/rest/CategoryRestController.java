package com.myshop.web.controllers.rest;

import com.myshop.services.services.CategoryService;
import com.myshop.web.models.CategoryViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryRestController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public List<CategoryViewModel> getCategories(){
        return this.categoryService.getAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CategoryViewModel.class))
                .collect(Collectors.toList());
    }

}

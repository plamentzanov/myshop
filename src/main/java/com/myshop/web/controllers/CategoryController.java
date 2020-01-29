package com.myshop.web.controllers;

import com.myshop.services.models.CategoryServiceModel;
import com.myshop.services.services.CategoryService;
import com.myshop.services.services.CloudinaryService;
import com.myshop.web.models.CategoryCreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String getCreateCategory(@ModelAttribute("category") CategoryCreateModel model){
        return "categories/create";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView createCategory(@Valid @ModelAttribute("category") CategoryCreateModel model, BindingResult bindingResult) throws IOException {
        if (!categoryService.isCategoryFree(model.getName())){
            bindingResult.addError(new FieldError("category","name", "Category already exists!"));
            return super.view("categories/create");
        }

        if (model.getImage().isEmpty()) {
            bindingResult.addError(new FieldError("category","image", "Image cannot be null!"));
            return super.view("categories/create");
        }

        CategoryServiceModel category = this.modelMapper.map(model, CategoryServiceModel.class);
        category.setImageUrl(cloudinaryService.upload(model.getImage()));
        this.categoryService.save(category);
        return super.redirect("/admins/products-manager");
    }

}

package com.myshop.web.controllers;

import com.myshop.services.models.ProductServiceModel;
import com.myshop.services.services.CloudinaryService;
import com.myshop.services.services.ProductService;
import com.myshop.web.models.ProductCreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class ProductController extends BaseController {

    private final ProductService productService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.productService = productService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/admins/products-manager/create-product")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String getCreateProduct(@ModelAttribute("product") ProductCreateModel model) {
        return "products/create-product";
    }

    @PostMapping("/admins/products-manager/create-product")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView createProduct(@Valid @ModelAttribute("product") ProductCreateModel model,
                                      BindingResult bindingResult) throws IOException {
        if (model.getImage().isEmpty()) {
            bindingResult.addError(new FieldError("product","image", "Image cannot be null!"));
            return super.view("products/create-product");
        }

        if (bindingResult.hasErrors()){
            return super.view("products/create-product");
        }

        ProductServiceModel product = this.modelMapper.map(model, ProductServiceModel.class);
        product.setImageUrl(this.cloudinaryService.upload(model.getImage()));
        this.productService.add(product);
        return super.view("admins/products-manager");
    }

    @GetMapping("/admins/products-manager/all-products")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String getAllProducts(){
        return "products/all-products-admin";
    }

}

package com.myshop.web.controllers;

import com.myshop.services.models.ProductServiceModel;
import com.myshop.services.services.CloudinaryService;
import com.myshop.services.services.ProductService;
import com.myshop.web.models.ProductCreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/products")
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

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String getCreateProduct(@ModelAttribute("product") ProductCreateModel model) {
        return "products/create";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView createProduct(@Valid @ModelAttribute("product") ProductCreateModel model, BindingResult bindingResult) throws IOException {
        if (model.getImage().isEmpty()) {
            bindingResult.addError(new FieldError("product", "image", "Image cannot be null!"));
            return super.view("products/create");
        }

        if (bindingResult.hasErrors()) {
            return super.view("products/create");
        }

        ProductServiceModel product = this.modelMapper.map(model, ProductServiceModel.class);
        product.setImageUrl(this.cloudinaryService.upload(model.getImage()));
        this.productService.add(product);
        return super.redirect("/products/all-admin");
    }

    @GetMapping("/all-admin")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public String getAllProducts() {
        return "products/all-admin";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView getEditProduct(@PathVariable String id, Model model) {
        ProductCreateModel product = this.modelMapper.map(this.productService.getById(id), ProductCreateModel.class);
        model.addAttribute("product", product);
        return super.view("products/edit");
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView editProduct(@PathVariable String id, @Valid @ModelAttribute("product") ProductCreateModel model, BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return super.view("products/edit");
        }

        ProductServiceModel product = this.modelMapper.map(model, ProductServiceModel.class);
        if (!model.getImage().isEmpty()) {
            product.setImageUrl(this.cloudinaryService.upload(model.getImage()));
        }
        this.productService.update(product, id);
        return super.redirect("/products/all-admin");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView deleteProduct(@PathVariable String id) {
        this.productService.delete(id);
        return super.redirect("/products/all-admin");
    }


}

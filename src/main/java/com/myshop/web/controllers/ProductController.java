package com.myshop.web.controllers;

import com.myshop.services.models.ProductServiceModel;
import com.myshop.services.services.CloudinaryService;
import com.myshop.services.services.ProductService;
import com.myshop.web.annotations.PageTitle;
import com.myshop.web.models.OrderCreateModel;
import com.myshop.web.models.ProductCreateModel;
import com.myshop.web.models.ProductViewModel;
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
import java.util.List;
import java.util.stream.Collectors;

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
    @PageTitle("Create Product")
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
    @PageTitle("All Products")
    public String getAllProductsAdmin() {
        return "products/all-admin";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    @PageTitle("Edit Product")
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

    @GetMapping("/select-category")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Categories")
    public String getAllProducts(){
        return "products/all-categories";
    }

    @GetMapping("/all/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Products By Category")
    public String getAllProductsByCategory(@PathVariable String id, Model model){
        List<ProductViewModel> products =  this.productService.getAllByCategoryId(id)
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("products" ,products);
        model.addAttribute("model", new OrderCreateModel());
        return "products/all-by-category";
    }
}
